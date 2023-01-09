package com.linuxea.lrpc.registry;

import com.linuxea.lrpc.registry.model.RegistryInfo;

/**
 * 注册接口
 */
public interface RegistryServer {

  /**
   * 注册
   */
  void registry(RegistryInfo registryInfo);

  /**
   * 续约注册
   */
  void renewRegistry(String  serviceId,String serviceName, Long seconds);

  /**
   * 取消注册
   */
  void unRegistry(RegistryInfo registryInfo);

}
