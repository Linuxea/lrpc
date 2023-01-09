package com.linuxea.lrpc.common.exception;

import com.linuxea.lrpc.common.model.RpcResponse;

public class CommonErrorRpcException extends BaseRpcResponseStatusException {


    protected CommonErrorRpcException() {
        super(RpcResponse.RpcResponseStatus.FAIL);
    }

    public CommonErrorRpcException(Throwable cause) {
        super(cause, RpcResponse.RpcResponseStatus.FAIL);
    }
}
