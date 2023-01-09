package com.linuxea.lrpc.common.exception;

import com.linuxea.lrpc.common.model.RpcResponse;

public class BaseRpcResponseStatusException extends RuntimeException {

    protected final RpcResponse.RpcResponseStatus responseStatus;

    protected BaseRpcResponseStatusException(RpcResponse.RpcResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public BaseRpcResponseStatusException(Throwable cause, RpcResponse.RpcResponseStatus responseStatus) {
        super(cause);
        this.responseStatus = responseStatus;
    }

    public BaseRpcResponseStatusException(Throwable cause) {
        super(cause);
        this.responseStatus = RpcResponse.RpcResponseStatus.FAIL;
    }

    public RpcResponse.RpcResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public RpcResponse buildResponse() {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setException(this);
        rpcResponse.setRpcResponseStatus(this.responseStatus);
        return rpcResponse;
    }

}
