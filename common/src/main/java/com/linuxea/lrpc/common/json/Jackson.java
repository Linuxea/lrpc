package com.linuxea.lrpc.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linuxea.lrpc.common.exception.JsonException;

/**
 * jackson implement
 */
public class Jackson implements Json {

  private final ObjectMapper objectMapper;

  public Jackson(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public String toString(Object obj) {
    try {
      return objectMapper.writer().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new JsonException(e);
    }
  }

  @Override
  public <T> T toObj(String json, Class<T> tClass) {
    try {
      return objectMapper.readValue(json, tClass);
    } catch (JsonProcessingException e) {
      System.out.println(json);
      throw new JsonException(e);
    }
  }
}
