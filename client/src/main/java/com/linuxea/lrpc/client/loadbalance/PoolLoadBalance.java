package com.linuxea.lrpc.client.loadbalance;

import com.linuxea.lrpc.common.model.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolLoadBalance implements ServiceLoadBalance {

  private final AtomicInteger counter = new AtomicInteger(0);

  @Override
  public Service selectOne(List<Service> services) {
    return services.get(counter.getAndAdd(1) % services.size());
  }
}
