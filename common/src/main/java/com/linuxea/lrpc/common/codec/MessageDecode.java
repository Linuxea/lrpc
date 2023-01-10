package com.linuxea.lrpc.common.codec;

import com.linuxea.lrpc.common.model.RpcMessage;

import java.io.InputStream;

public interface MessageDecode {

    RpcMessage decode(InputStream inputStream) throws Exception;

}
