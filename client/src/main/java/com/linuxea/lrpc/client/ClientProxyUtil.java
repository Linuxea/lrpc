package com.linuxea.lrpc.client;

import com.linuxea.lrpc.client.discovery.ServiceDiscoverer;
import com.linuxea.lrpc.client.loadbalance.ServiceLoadBalance;
import com.linuxea.lrpc.client.net.NetClient;
import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.common.model.Service;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.UUID;

public class ClientProxyUtil implements InvocationHandler {

  private final ServiceDiscoverer serviceDiscoverer;
  private final ServiceLoadBalance serviceLoadBalance;
  private final NetClient netClient;
  private final Class<?> targetClazz;
  private final Object target;

  public ClientProxyUtil(ServiceDiscoverer serviceDiscoverer, ServiceLoadBalance serviceLoadBalance,
      NetClient netClient, Class<?> targetClazz, Object target) {
    this.serviceDiscoverer = serviceDiscoverer;
    this.serviceLoadBalance = serviceLoadBalance;
    this.netClient = netClient;
    this.targetClazz = targetClazz;
    this.target = target;
  }


  @SuppressWarnings("all")
  public <T> T getProxyClient(Class<T> tClass) {
    return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass.getClass()},
        this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    //1. 获得服务信息
    String serviceName = targetClazz.getName();
    List<Service> services = serviceDiscoverer.discovery(serviceName);
    Service selectOne = serviceLoadBalance.selectOne(services);

    //2. 构建request对象
    RpcRequest rpcRequest = new RpcRequest();
    rpcRequest.setRequestId(UUID.randomUUID().toString());
    rpcRequest.setServiceName(serviceName);
    rpcRequest.setMethod(method.getName());
    rpcRequest.setParametersTypes(method.getParameterTypes());
    rpcRequest.setParameters(args);

    //3. 发送请求
    RpcResponse rpcResponse = netClient.sendReq(rpcRequest, selectOne);
    return rpcResponse.getReturnValue();
  }


}