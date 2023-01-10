package com.linuxea.lrpc.server;

import com.linuxea.lrpc.common.model.Service;

public class ServiceObj {

  /**
   * 服务名称
   */
  private String name;

  /**
   * 服务Class
   */
  private Class<?> clazz;

  /**
   * 具体服务
   */
  private Object obj;

  public Service getService(String host, int port, byte serialize, byte compress) {
    Service service = new Service();
    service.setIp(host);
    service.setPort(port);
    service.setServiceName(this.name);
    service.setSerialize(serialize);
    service.setCompress(compress);
    return service;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Class<?> getClazz() {
    return clazz;
  }

  public void setClazz(Class<?> clazz) {
    this.clazz = clazz;
  }

  public Object getObj() {
    return obj;
  }

  public void setObj(Object obj) {
    this.obj = obj;
  }
}
