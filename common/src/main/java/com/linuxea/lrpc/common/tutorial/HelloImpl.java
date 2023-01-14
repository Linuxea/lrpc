package com.linuxea.lrpc.common.tutorial;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloImpl implements Hello {

  @Override
  public String hello(String name) {
    return "hello:" + name;
  }

  @Override
  public String now() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }
}
