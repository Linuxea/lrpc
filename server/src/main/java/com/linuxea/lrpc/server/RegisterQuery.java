package com.linuxea.lrpc.server;


/**
 * 服务查询
 */
public interface RegisterQuery {

  ServiceObj get(String name);

}
