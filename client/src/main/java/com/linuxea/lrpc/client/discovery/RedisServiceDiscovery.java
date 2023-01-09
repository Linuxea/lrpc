package com.linuxea.lrpc.client.discovery;

import com.linuxea.lrpc.common.json.Json;
import com.linuxea.lrpc.common.model.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import redis.clients.jedis.Jedis;

public class RedisServiceDiscovery implements ServiceDiscoverer {

  private final Jedis jedis;
  private final Json json;

  public RedisServiceDiscovery(Jedis jedis, Json json) {
    this.jedis = jedis;
    this.json = json;
  }

  @Override
  public List<Service> discovery(String serviceName) {
    String servicePath = "/rpc" + "/" + serviceName + "/service";
    Set<String> members = this.jedis.smembers(servicePath);
    return members.stream().map(member -> this.json.toObj(member, Service.class))
        .collect(Collectors.toList());
  }
}
