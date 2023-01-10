package com.linuxea.lrpc.common.codec;

import com.linuxea.lrpc.common.model.RpcMessage;

public interface MessageDecode {

    RpcMessage decode(byte[] bytes) throws Exception;

}
