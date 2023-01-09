package com.linuxea.lrpc.common.model;

public class RpcUnknownResponse extends RpcResponse {

  public RpcUnknownResponse(Exception e) {
    this.setException(e);
    this.setRpcResponseStatus(RpcResponseStatus.FAIL);
  }



}
