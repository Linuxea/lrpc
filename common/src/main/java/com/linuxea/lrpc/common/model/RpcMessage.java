package com.linuxea.lrpc.common.model;

import java.io.Serializable;

public class RpcMessage implements Serializable {


  /**
   * rpc message data field
   */
  private Object data;

  /**
   * date field compress algorithm
   */
  private String compress;

  /**
   * data field serialization algorithm
   */
  private String serialize;

  public RpcMessage() {
  }

  public RpcMessage(Object data) {
    this.data = data;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getCompress() {
    return compress;
  }

  public void setCompress(String compress) {
    this.compress = compress;
  }

  public String getSerialize() {
    return serialize;
  }

  public void setSerialize(String serialize) {
    this.serialize = serialize;
  }
}
