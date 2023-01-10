package com.linuxea.lrpc.client.net;

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

  public ShortConnectNetClient(AbstractMessageCodecFactory abstractMessageCodecFactory) {
    this.abstractMessageCodecFactory = abstractMessageCodecFactory;
  }

  @Override
  public RpcResponse sendReq(RpcRequest rpcRequest, Service service) throws Exception {

    String ip = service.getIp();
    Integer port = service.getPort();
    try (Socket socket = new Socket(ip, port); OutputStream outputStream = socket.getOutputStream(); InputStream socketInputStream = socket.getInputStream()) {

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
}
