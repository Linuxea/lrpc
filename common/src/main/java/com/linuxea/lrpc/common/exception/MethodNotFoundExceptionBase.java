package com.linuxea.lrpc.common.exception;

import com.linuxea.lrpc.common.model.RpcResponse;

public class MethodNotFoundExceptionBase extends BaseRpcResponseStatusException {


    public MethodNotFoundExceptionBase() {
        super(RpcResponse.RpcResponseStatus.NOT_FOUND);
    }

    public MethodNotFoundExceptionBase(Throwable cause, RpcResponse.RpcResponseStatus responseStatus) {
        super(cause, responseStatus);
    }

    public MethodNotFoundExceptionBase(Throwable cause) {
        super(cause, RpcResponse.RpcResponseStatus.NOT_FOUND);
    }

}
