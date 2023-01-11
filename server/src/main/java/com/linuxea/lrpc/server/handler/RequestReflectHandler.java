package com.linuxea.lrpc.server.handler;

import com.linuxea.lrpc.common.exception.BaseRpcResponseStatusException;
import com.linuxea.lrpc.common.exception.MethodNotFoundExceptionBase;
import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.server.RegisterQuery;
import com.linuxea.lrpc.server.ServiceObj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestReflectHandler extends BaseHandler {

    public RequestReflectHandler(RegisterQuery registerQuery) {
        super(registerQuery);
    }

    @Override
    public RpcResponse invoke(ServiceObj serviceObject, RpcRequest request) {
        Method method;
        try {
            method = serviceObject.getClazz()
                    .getMethod(request.getMethod(), request.getParametersTypes());
        } catch (NoSuchMethodException e) {
            throw new MethodNotFoundExceptionBase(e);
        }

        Object invoke;
        try {
            invoke = method.invoke(serviceObject.getObj(), request.getParameters());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new BaseRpcResponseStatusException(e);
        }
        RpcResponse response = new RpcResponse();
        response.setReturnValue(invoke);
        return response;
    }
}
