package com.linuxea.lrpc.server.handler;

import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.server.RegisterQuery;
import com.linuxea.lrpc.server.ServiceObj;

public abstract class BaseHandler {


  protected RegisterQuery registerQuery;

  public RpcResponse handleRequest(RpcRequest request) {
    //1. local service obj
    ServiceObj serviceObj = registerQuery.get(request.getServiceName());
    RpcResponse response;
    try {
      //2. 调用子类对应的实现方法
      response = invoke(serviceObj, request);
    } catch (Exception e) {
      response = new RpcResponse();
      response.setException(e);
    }
    //响应
    response.setRequestId(request.getRequestId());
    return response;
  }

  /**
   * 具体代理调用
   *
   * @return RpcResponse
   */
  public abstract RpcResponse invoke(ServiceObj serviceObject, RpcRequest request) throws Exception;

}
