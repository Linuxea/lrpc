package com.linuxea.lrpc.common.serialize;

import java.io.IOException;

/**
 * 序列化工厂
 */
public interface SerializeFactory {


    /**
     * 序列化
     * @param object
     * @return
     */
    byte[] serialize(Object object) throws Exception;


    /**
     * 反序列化
     * @param data
     * @param tClass
     * @return
     * @param <T>
     */
    <T> T deserialize(byte[] data, Class<T> tClass) throws Exception;

}
