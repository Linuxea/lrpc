package com.linuxea.lrpc.server.handler;

import com.linuxea.lrpc.common.exception.BaseRpcResponseStatusException;
import com.linuxea.lrpc.common.exception.MethodNotFoundExceptionBase;
import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.common.model.RpcUnknownResponse;
import com.linuxea.lrpc.server.RegisterQuery;
import com.linuxea.lrpc.server.ServiceObj;

public abstract class BaseHandler {


  protected final RegisterQuery registerQuery;

  protected BaseHandler(RegisterQuery registerQuery) {
    this.registerQuery = registerQuery;
  }

  public RpcResponse handleRequest(RpcRequest request) {
    //1. local service obj
    ServiceObj serviceObj = registerQuery.get(request.getServiceName());
    RpcResponse response;
    try {
      if (serviceObj == null) {
        throw new MethodNotFoundExceptionBase();
      }

      //2. 调用子类对应的实现方法
      response = invoke(serviceObj, request);
    } catch (BaseRpcResponseStatusException e) {
      return e.buildResponse();
    } catch (Exception e) {
      return new RpcUnknownResponse(e);
    }
    //响应
    response.setRequestId(request.getRequestId());
    response.setRpcResponseStatus(RpcResponse.RpcResponseStatus.SUCCESS);
    return response;
  }

  /**
   * 具体代理调用
   *
   * @return RpcResponse
   */
  public abstract RpcResponse invoke(ServiceObj serviceObject, RpcRequest request);

}
