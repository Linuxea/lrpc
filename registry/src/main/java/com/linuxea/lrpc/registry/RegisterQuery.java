package com.linuxea.lrpc.registry;

import com.linuxea.lrpc.registry.model.RegistryInfo;
import java.util.List;

/**
 * 服务查询
 */
public interface RegisterQuery {


  /**
   * 通过服务名称获取注册信息列表
   *
   * @param serviceName 服务名称
   * @return 服务信息列表
   */
  List<RegistryInfo> listService(String serviceName);

}
