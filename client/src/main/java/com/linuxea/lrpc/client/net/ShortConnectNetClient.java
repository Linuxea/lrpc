package com.linuxea.lrpc.client.net;

import com.linuxea.lrpc.client.net.socket.SocketGenerate;
import com.linuxea.lrpc.common.codec.AbstractMessageCodecFactory;
import com.linuxea.lrpc.common.codec.MessageDecode;
import com.linuxea.lrpc.common.codec.MessageEncode;
import com.linuxea.lrpc.common.model.RpcMessage;
import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.common.model.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ShortConnectNetClient implements NetClient {

    private final AbstractMessageCodecFactory abstractMessageCodecFactory;
    private final SocketGenerate socketGenerate;

    public ShortConnectNetClient(AbstractMessageCodecFactory abstractMessageCodecFactory, SocketGenerate socketGenerate) {
        this.abstractMessageCodecFactory = abstractMessageCodecFactory;
        this.socketGenerate = socketGenerate;
    }

    @Override
    public RpcResponse sendReq(RpcRequest rpcRequest, Service service) throws Exception {

        String ip = service.getIp();
        Integer port = service.getPort();
        Socket socket = socketGenerate.get(ip, port);
        OutputStream outputStream = socket.getOutputStream();
        InputStream socketInputStream = socket.getInputStream();
        RpcMessage rpcMessage = new RpcMessage();
        rpcMessage.setData(rpcRequest);
        rpcMessage.setSerialize(service.getSerialize());
        rpcMessage.setCompress(service.getCompress());

        MessageEncode encode = this.abstractMessageCodecFactory.encode();
        outputStream.write(encode.encode(rpcMessage));
        // resp
        MessageDecode decode = this.abstractMessageCodecFactory.decode();
        return (RpcResponse) decode.decode(socketInputStream).getData();
    }
}
