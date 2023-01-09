package com.linuxea.lrpc.server;

import com.linuxea.lrpc.common.model.RpcMessage;
import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.common.tutorial.Hello;
import com.linuxea.lrpc.common.tutorial.HelloImpl;
import com.linuxea.lrpc.server.handler.BaseHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketRpcServer extends RpcServer {

  private final BaseHandler baseHandler;
  private ServerSocket serverSocket;

  public ServerSocketRpcServer(int port, RegistryServer registryServer, BaseHandler baseHandler) {
    super(port, registryServer);
    this.baseHandler = baseHandler;
  }

  @Override
  public void start() throws Exception {
    // 注册所有服务 tutorial
    ServiceObj serviceObj = new ServiceObj();
    serviceObj.setName(Hello.class.getName());
    serviceObj.setClazz(Hello.class);
    serviceObj.setObj(new HelloImpl());
    this.registryServer.registry(serviceObj);


    serverSocket = new ServerSocket(port);
    while (true) {
      Socket accept = serverSocket.accept();
      new Thread(new processReq(accept)).start();
    }

  }

  @Override
  public void stop() throws Exception {
    this.serverSocket.close();
  }

  private class processReq implements Runnable {

    private final Socket accept;

    private processReq(Socket accept) {
      this.accept = accept;
    }

    @Override
    public void run() {

      while (true) {
        try (InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream()) {

          byte[] read = new byte[1024];
          int offset = inputStream.read(read);

          byte[] dest = new byte[offset];
          System.arraycopy(read, 0, dest, 0, offset);


          try (ByteArrayInputStream bis = new ByteArrayInputStream(dest);
              ObjectInput in = new ObjectInputStream(bis)) {
            RpcMessage rpcMessage = (RpcMessage) in.readObject();
            RpcRequest rpcRequest = (RpcRequest) rpcMessage.getData();
            RpcResponse rpcResponse = baseHandler.handleRequest(rpcRequest);

            // write back
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bos)) {
              out.writeObject(rpcResponse);
              out.flush();
              outputStream.write(bos.toByteArray());
            }
          }
        } catch (IOException | ClassNotFoundException e) {
          throw new RuntimeException(e);
        }
      }

    }
  }
}
