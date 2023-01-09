package com.linuxea.lrpc.client;

import com.linuxea.lrpc.server.ServerStub;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientStub implements InvocationHandler {

  private final ServerStub serverStub;

  public ClientStub(ServerStub serverStub) {
    this.serverStub = serverStub;
  }

  public void sendReq(Object proxy, Method method, Object[] args) {
    Object newProxyInstance = Proxy.newProxyInstance(
        proxy.getClass().getClassLoader(),
        new Class[]{proxy.getClass()}, this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return serverStub.dealIncome(proxy.getClass(), method.getName(), method.getReturnType(),
        method.getParameterTypes(), args);
  }
}
