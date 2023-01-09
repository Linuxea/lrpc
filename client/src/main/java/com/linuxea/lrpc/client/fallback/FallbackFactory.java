package com.linuxea.lrpc.client.fallback;

import com.linuxea.lrpc.common.model.RpcResponse;

public interface FallbackFactory {

    RpcResponse build(Exception e);

}
