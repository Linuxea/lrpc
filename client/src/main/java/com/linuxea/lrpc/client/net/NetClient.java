package com.linuxea.lrpc.client.net;

import com.linuxea.lrpc.common.model.RpcRequest;
import com.linuxea.lrpc.common.model.RpcResponse;
import com.linuxea.lrpc.common.model.Service;
import java.io.IOException;

/**
 * 客户端网络
 */
public interface NetClient {


  /**
   * 发送网络请求
   *
   * @param rpcRequest rpc 请求对象封装
   * @param service    使用服务
   * @return {@link com.linuxea.lrpc.common.model.RpcResponse}
   */
  RpcResponse sendReq(RpcRequest rpcRequest, Service service) throws Exception;


}
