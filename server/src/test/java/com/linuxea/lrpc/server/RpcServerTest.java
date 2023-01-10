package com.linuxea.lrpc.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linuxea.lrpc.common.codec.LengthFieldCodec;
import com.linuxea.lrpc.common.json.Jackson;
import com.linuxea.lrpc.common.json.Json;
import com.linuxea.lrpc.server.handler.BaseHandler;
import com.linuxea.lrpc.server.handler.RequestReflectHandler;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class RpcServerTest {


  @Test
  public void testServer() throws Exception {

    Jedis jedis = new Jedis("redis-host.com", 6379);
    Json jackson = new Jackson(new ObjectMapper());
    RegistryServer redisRegisterServer = new RedisRegisterServer(jedis, jackson, 9090, "jdk", "gzip");
    BaseHandler requestReflectHandler = new RequestReflectHandler((RegisterQuery) redisRegisterServer);
    RpcServer rpcServer = new ServerSocketRpcServer(9090, redisRegisterServer, requestReflectHandler, new LengthFieldCodec());

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        rpcServer.stop();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }));

    rpcServer.start();

  }

}
