package com.linuxea.lrpc.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LocalRegisterServer implements RegistryServer, RegisterQuery {

  protected final Map<String, ServiceObj> cache = new ConcurrentHashMap<>();
  protected String protocol;
  protected String compress;
  protected Integer serverPort;

  @Override
  public void registry(ServiceObj serviceObj) throws Exception {
    this.cache.putIfAbsent(serviceObj.getName(), serviceObj);
  }

  @Override
  public ServiceObj get(String name) {
    return cache.get(name);
  }

  @Override
  public void remove() throws Exception {
    this.cache.clear();
  }
}
