package com.linuxea.lrpc.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linuxea.lrpc.client.discovery.RedisServiceDiscovery;
import com.linuxea.lrpc.client.discovery.ServiceDiscoverer;
import com.linuxea.lrpc.client.loadbalance.PoolLoadBalance;
import com.linuxea.lrpc.client.loadbalance.ServiceLoadBalance;
import com.linuxea.lrpc.client.net.NetClient;
import com.linuxea.lrpc.client.net.ShortConnectNetClient;
import com.linuxea.lrpc.common.codec.LengthFieldCodec;
import com.linuxea.lrpc.common.json.Jackson;
import com.linuxea.lrpc.common.json.Json;
import com.linuxea.lrpc.common.tutorial.Hello;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class RpcClientTest {


  @Test
  public void testClient() {
    Jedis jedis = new Jedis("redis-host.com", 6379);
    Json jackson = new Jackson(new ObjectMapper());
    ServiceDiscoverer redisServiceDiscovery = new RedisServiceDiscovery(jedis, jackson);
    ServiceLoadBalance ServiceLoadBalance = new PoolLoadBalance();

    NetClient shortConnectNetClient = new ShortConnectNetClient(new LengthFieldCodec());

    ClientProxyFactory clientProxyFactory = new ClientProxyFactory(redisServiceDiscovery, ServiceLoadBalance, shortConnectNetClient, Hello.class);

    Hello hello = clientProxyFactory.getProxyClient();

    System.out.println(hello.hello("linuxea"));
    System.out.println(hello.hello("summer"));
    System.out.println(hello.hello("abcdefg"));
    System.out.println(hello.hello("i-love-china"));


  }


}
