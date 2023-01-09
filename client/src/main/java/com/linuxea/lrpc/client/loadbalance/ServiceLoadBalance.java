package com.linuxea.lrpc.client.loadbalance;

import java.security.Provider.Service;
import java.util.List;

/**
 * 负载均衡
 */
public interface ServiceLoadBalance {


  Service selectOne(List<Service> services);


}
