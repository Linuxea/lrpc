package com.linuxea.lrpc.server;

import com.linuxea.lrpc.common.codec.AbstractMessageCodecFactory;
import com.linuxea.lrpc.common.codec.MessageDecode;
import com.linuxea.lrpc.common.codec.MessageEncode;
import com.linuxea.lrpc.common.model.RpcMessage;
import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.common.tutorial.Hello;
import com.linuxea.lrpc.common.tutorial.HelloImpl;
import com.linuxea.lrpc.server.handler.BaseHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadFactory;

public class ServerSocketRpcServer extends RpcServer {

  private final BaseHandler baseHandler;
  private ServerSocket serverSocket;
  private final AbstractMessageCodecFactory abstractMessageCodecFactory;
  private final ThreadFactory threadFactory = new ServerThreadFactory();

  public ServerSocketRpcServer(int port, RegistryServer registryServer, BaseHandler baseHandler, AbstractMessageCodecFactory abstractMessageCodecFactory) {
    super(port, registryServer);
    this.baseHandler = baseHandler;
    this.abstractMessageCodecFactory = abstractMessageCodecFactory;
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
    while (!serverSocket.isClosed()) {
      try {
        Socket accept = serverSocket.accept();
        threadFactory.newThread(new processReq(accept)).start();
      } catch (Exception e) {
        break;
      }
    }

  }

  @Override
  public void stop() throws Exception {
    this.serverSocket.close();
    this.registryServer.remove();
  }

  private class processReq implements Runnable {

    private final Socket accept;

    private processReq(Socket accept) {
      this.accept = accept;
    }

    @Override
    public void run() {

      while (true) {
        try (InputStream inputStream = accept.getInputStream(); OutputStream outputStream = accept.getOutputStream()) {

          MessageDecode decode = abstractMessageCodecFactory.decode();
          RpcMessage rpcMessage = decode.decode(inputStream);

          RpcRequest rpcRequest = (RpcRequest) rpcMessage.getData();
          RpcResponse rpcResponse = baseHandler.handleRequest(rpcRequest);
          // send resp
          MessageEncode encode = abstractMessageCodecFactory.encode();
          byte[] respBytes = encode.encode(new RpcMessage(rpcResponse, rpcMessage.getCompress(), rpcMessage.getSerialize()));
          outputStream.write(respBytes);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }

    }
  }
}
