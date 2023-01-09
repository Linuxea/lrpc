package com.linuxea.lrpc.common.tutorial;

public class HelloImpl implements Hello {

  @Override
  public String hello(String name) {
    return "hello:" + name;
  }
}
