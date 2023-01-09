package com.linuxea.lrpc.client.loadbalance;

import com.linuxea.lrpc.common.model.Service;
import java.util.List;

/**
 * 负载均衡
 */
public interface ServiceLoadBalance {


  Service selectOne(List<Service> services);


}
