package com.linuxea.lrpc.common.json;

public interface Json {


  /**
   * to json string
   *
   * @param obj obj
   * @return json string
   */
  String toString(Object obj);

  /**
   * json to obj
   *
   * @param json   json string
   * @param tClass obj Class
   * @param <T>    Class type
   * @return instance of Class
   */
  <T> T toObj(String json, Class<T> tClass);

}
