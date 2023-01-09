package com.linuxea.lrpc.server.handler;

import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.server.RegisterQuery;
import com.linuxea.lrpc.server.ServiceObj;
import java.lang.reflect.Method;

public class RequestReflectHandler extends BaseHandler {

  public RequestReflectHandler(RegisterQuery registerQuery) {
    super(registerQuery);
  }

  @Override
  public RpcResponse invoke(ServiceObj serviceObject, RpcRequest request) throws Exception {
    Method method = serviceObject.getClazz()
        .getMethod(request.getMethod(), request.getParametersTypes());
    Object invoke = method.invoke(serviceObject.getObj(), request.getParameters());
    RpcResponse response = new RpcResponse();
    response.setReturnValue(invoke);
    return response;
  }
}
