package com.linuxea.lrpc.client.net;

import com.linuxea.lrpc.common.model.RpcMessage;
import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.common.model.Service;
import com.linuxea.lrpc.common.serialize.SerializeFactory;
import com.linuxea.lrpc.common.serialize.SerializeFactoryBuilder;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ShortConnectNetClient implements NetClient {

  @Override
  public RpcResponse sendReq(RpcRequest rpcRequest, Service service) throws Exception {

    String ip = service.getIp();
    Integer port = service.getPort();
    try (Socket socket = new Socket(ip, port); OutputStream outputStream = socket.getOutputStream(); InputStream socketInputStream = socket.getInputStream()) {

      RpcMessage rpcMessage = new RpcMessage();
      rpcMessage.setData(rpcRequest);
      rpcMessage.setSerialize(service.getSerialize());
      rpcMessage.setCompress(service.getCompress());

      SerializeFactory serializeFactory = SerializeFactoryBuilder.build(service.getSerialize());
      // req
      outputStream.write(serializeFactory.serialize(rpcMessage));
      // resp
      byte[] readAllBytes = socketInputStream.readAllBytes();
      RpcMessage respRpcMessage = serializeFactory.deserialize(readAllBytes, RpcMessage.class);
      return (RpcResponse) respRpcMessage.getData();
    }
  }
}
