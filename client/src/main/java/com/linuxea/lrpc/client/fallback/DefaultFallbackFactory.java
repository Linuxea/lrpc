package com.linuxea.lrpc.client.fallback;

import com.linuxea.lrpc.common.exception.BaseRpcResponseStatusException;
import com.linuxea.lrpc.common.model.RpcResponse;

public class DefaultFallbackFactory implements FallbackFactory {

    @Override
    public RpcResponse build(Exception e) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setReturnValue(null);
        rpcResponse.setException(e);
        if (e instanceof BaseRpcResponseStatusException) {
            rpcResponse.setRpcResponseStatus(((BaseRpcResponseStatusException) e).getResponseStatus());
        }
        return rpcResponse;
    }
}
