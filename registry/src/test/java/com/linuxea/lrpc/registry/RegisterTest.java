package com.linuxea.lrpc.registry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linuxea.lrpc.registry.json.Jackson;
import com.linuxea.lrpc.registry.json.Json;
import com.linuxea.lrpc.registry.model.RegistryInfo;
import java.util.List;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class RegisterTest {

  Jedis jedis = new Jedis("redis-host.com", 6379);
  Json json = new Jackson(new ObjectMapper());
  RegistryServer redisRegisterServer = new RedisRegisterServer(jedis, json);
  RegisterQuery registerQuery = new RedisRegisterServer(jedis, json);

  @Test
  public void registerTest() {
    RegistryInfo registryInfo = new RegistryInfo();
    registryInfo.setLiveSeconds(50L);
    registryInfo.setIp("192.168.31.40");
    registryInfo.setPort(8080);
    registryInfo.setServiceName("user-service");
    registryInfo.setClassName("com.linuxea.service.UserService");
    registryInfo.setMethodSignature("L(Long)");
    redisRegisterServer.registry(registryInfo);
  }

  @Test
  public void renewRegister() {
    redisRegisterServer.renewRegistry("e3bb5d7c-e3b5-4b48-89ec-9bb0a401be24", "user-service", 30L);
  }

  @Test
  public void listRegisterService() {
    List<RegistryInfo> registryInfos = registerQuery.listService("user-service");
    registryInfos.forEach(System.out::println);
  }

}
