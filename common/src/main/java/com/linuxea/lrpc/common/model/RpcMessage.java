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
  private byte compress;

  /**
   * data field serialization algorithm
   */
  private byte serialize;

  public RpcMessage() {
  }

  public RpcMessage(Object data) {
    this.data = data;
  }

  public RpcMessage(Object data, byte compress, byte serialize) {
    this.data = data;
    this.compress = compress;
    this.serialize = serialize;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public byte getCompress() {
    return compress;
  }

  public void setCompress(byte compress) {
    this.compress = compress;
  }

  public byte getSerialize() {
    return serialize;
  }

  public void setSerialize(byte serialize) {
    this.serialize = serialize;
  }
}
