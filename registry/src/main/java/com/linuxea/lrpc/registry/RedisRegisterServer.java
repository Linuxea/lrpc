package com.linuxea.lrpc.registry;

import com.linuxea.lrpc.registry.json.Json;
import com.linuxea.lrpc.registry.model.RegistryInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

public class RedisRegisterServer implements RegistryServer, RegisterQuery {

  private final Jedis jedis;
  private final Json json;
  private final String Splitter = "#split#";

  public RedisRegisterServer(Jedis jedis, Json json) {
    this.jedis = jedis;
    this.json = json;
  }

  @Override
  public void registry(RegistryInfo registryInfo) {

    String uuid = UUID.randomUUID().toString();
    registryInfo.setServiceId(uuid);

    this.jedis.setex(registryInfo.getServiceName() + Splitter + registryInfo.getServiceId(),
        registryInfo.getLiveSeconds(), json.toString(registryInfo));
  }

  @Override
  public void renewRegistry(String serviceId, String serviceName, Long seconds) {
    this.jedis.expire(serviceName + Splitter + serviceId, seconds);
  }


  @Override
  public void unRegistry(RegistryInfo registryInfo) {
    this.jedis.del(registryInfo.getServiceName() + Splitter + registryInfo.getServiceId());
  }

  @Override
  public List<RegistryInfo> listService(String serviceName) {
    ScanParams scanParams = new ScanParams();
    scanParams.match(serviceName + "*");
    List<List<String>> keys = new ArrayList<>();
    while (true) {
      ScanResult<String> scanResult = this.jedis.scan(ScanParams.SCAN_POINTER_START, scanParams,
          "string");
      List<String> result = scanResult.getResult();
      keys.add(result);
      if (scanResult.getCursor().equals(ScanParams.SCAN_POINTER_START)) {
        break;
      }
    }

    String[] keysFlat = keys.stream().flatMap(List::stream).toArray(String[]::new);
    if (keysFlat.length == 0) {
      return new ArrayList<>();
    }
    return this.jedis.mget(keysFlat).stream().map(val -> this.json.toObj(val, RegistryInfo.class))
        .collect(Collectors.toList());
  }
}
