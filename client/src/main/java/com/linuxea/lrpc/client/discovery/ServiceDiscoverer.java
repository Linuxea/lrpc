package com.linuxea.lrpc.client.discovery;

import com.linuxea.lrpc.common.model.Service;
import java.util.List;

/**
 * 服务发现
 */
public interface ServiceDiscoverer {

  List<Service> discovery(String serviceName);

}
