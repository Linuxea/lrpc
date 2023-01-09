package com.linuxea.lrpc.server;


import java.net.UnknownHostException;

/**
 * 注册接口
 */
public interface RegistryServer {

  /**
   * 注册
   */
  void registry(ServiceObj serviceObj) throws Exception;


  /**
   * 移除注册
   */
  void remove() throws UnknownHostException, Exception;


}
