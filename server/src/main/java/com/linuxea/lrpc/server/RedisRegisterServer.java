package com.linuxea.lrpc.server;

import com.linuxea.lrpc.common.json.Json;
import com.linuxea.lrpc.common.model.Service;
import redis.clients.jedis.Jedis;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class RedisRegisterServer extends LocalRegisterServer {

    private final Jedis jedis;
    private final Json json;
    private final List<String> remotePathUrl = new ArrayList<>();

    public RedisRegisterServer(Jedis jedis, Json json, Integer serverPort, byte serialize, byte compress) {
        super(serialize, compress);
        this.jedis = jedis;
        this.json = json;
        this.serverPort = serverPort;
        super.serialize = serialize;
        super.compress = compress;
    }

    @Override
    public void registry(ServiceObj serviceObj) throws Exception {
        super.registry(serviceObj);
        // 注册到 redis
        String host = InetAddress.getLocalHost().getHostAddress();
        Service service = serviceObj.getService(host, serverPort, super.serialize, super.compress);
        String servicePath = "/rpc" + "/" + service.getServiceName() + "/service";
        this.jedis.sadd(servicePath, this.json.toString(service));
        remotePathUrl.add(servicePath);
    }

    @Override
    public void remove() throws Exception {
        super.remove();
        remotePathUrl.forEach(this.jedis::del);
    }
}
