package com.linuxea.lrpc.common.model;

import java.io.Serializable;

public class RpcResponse implements Serializable {

  private String requestId;


  private Object returnValue;

  private Exception exception;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Object getReturnValue() {
    return returnValue;
  }

  public void setReturnValue(Object returnValue) {
    this.returnValue = returnValue;
  }

  public Exception getException() {
    return exception;
  }

  public void setException(Exception exception) {
    this.exception = exception;
  }
}
