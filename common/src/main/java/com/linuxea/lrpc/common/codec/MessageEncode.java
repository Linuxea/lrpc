package com.linuxea.lrpc.common.codec;

import com.linuxea.lrpc.common.model.RpcMessage;

public interface MessageEncode {


    byte[] encode(RpcMessage obj) throws Exception;

}
