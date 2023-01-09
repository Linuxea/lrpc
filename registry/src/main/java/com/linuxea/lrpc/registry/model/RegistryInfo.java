package com.linuxea.lrpc.registry.model;

/**
 * 注册信息
 */
public class RegistryInfo {

  /**
   * identity id
   */
  private String serviceId;

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
   * 类名
   */
  private String className;


  /**
   * 方法名签名
   */
  private String methodSignature;
  /**
   * 注册生命时长 秒
   */
  private Long liveSeconds;


  public Long getLiveSeconds() {
    return liveSeconds;
  }

  public void setLiveSeconds(Long liveSeconds) {
    this.liveSeconds = liveSeconds;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

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

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getMethodSignature() {
    return methodSignature;
  }

  public void setMethodSignature(String methodSignature) {
    this.methodSignature = methodSignature;
  }
}

