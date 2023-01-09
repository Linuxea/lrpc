package com.linuxea.lrpc.registry.json;

public interface Json {


  /**
   * to json string
   * @param obj obj
   * @return json string
   */
  String toString(Object obj);

  /**
   * json to obj
   * @param json json string
   * @param tClass obj Class
   * @return instance of Class
   * @param <T> Class type
   */
  <T> T toObj(String json, Class<T> tClass);

}
