package com.linuxea.lrpc.common.model;

import java.io.Serializable;

public class RpcMessage implements Serializable {


  private Object data;


  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
