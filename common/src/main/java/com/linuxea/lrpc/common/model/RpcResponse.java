package com.linuxea.lrpc.common.model;

import java.io.Serializable;

public class RpcResponse implements Serializable {

  private String requestId;

  private Object returnValue;

  private Exception exception;

  private RpcResponseStatus rpcResponseStatus;


  public enum RpcResponseStatus {

    SUCCESS(200, "SUCCESS"),

    FAIL(500, "FAIL"),

    NOT_FOUND(404, "NOT_FOUND"),

    ;

    private final Integer code;
    private final String desc;

    RpcResponseStatus(Integer code, String desc) {
      this.code = code;
      this.desc = desc;
    }
  }

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

  public RpcResponseStatus getRpcResponseStatus() {
    return rpcResponseStatus;
  }

  public void setRpcResponseStatus(RpcResponseStatus rpcResponseStatus) {
    this.rpcResponseStatus = rpcResponseStatus;
  }
}
