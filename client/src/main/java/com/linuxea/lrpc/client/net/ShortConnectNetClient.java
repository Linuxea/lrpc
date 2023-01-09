package com.linuxea.lrpc.client.net;

import com.linuxea.lrpc.common.model.RpcMessage;
import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.common.model.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ShortConnectNetClient implements NetClient {

  @Override
  public RpcResponse sendReq(RpcRequest rpcRequest, Service service) throws Exception {

    String ip = service.getIp();
    Integer port = service.getPort();
    try (Socket socket = new Socket(ip, port);
        OutputStream outputStream = socket.getOutputStream();
        InputStream socketInputStream = socket.getInputStream()) {

      RpcMessage rpcMessage = new RpcMessage();
      rpcMessage.setData(rpcRequest);

      byte[] yourBytes;

      try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
          ObjectOutputStream out = new ObjectOutputStream(bos)) {
        out.writeObject(rpcMessage);
        out.flush();
        yourBytes = bos.toByteArray();
      }

      outputStream.write(yourBytes);
      byte[] readAllBytes = socketInputStream.readAllBytes();

      try (ByteArrayInputStream bis = new ByteArrayInputStream(readAllBytes);
          ObjectInput in = new ObjectInputStream(bis)) {
        return (RpcResponse) in.readObject();
      }
    }
  }
}
