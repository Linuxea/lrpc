package com.linuxea.lrpc.common.model;

/**
 * 服务
 */
public class Service {

  /**
   * 服务 ip
   */
  private String ip;

  /**
   * 服务端口
   */
  private Integer port;

  /**
   * 服务名称
   */
  private String serviceName;

  /**
   * 服务协议
   */
  private String protocol;

  /**
   * 压缩协议
   */
  private String compress;


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getCompress() {
    return compress;
  }

  public void setCompress(String compress) {
    this.compress = compress;
  }
}

