package com.linuxea.lrpc.common.serialize;

import java.io.*;

public class JdkSerializeFactory implements SerializeFactory {


    @Override
    public byte[] serialize(Object object) throws Exception {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            out.flush();
            return bos.toByteArray();
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) throws Exception {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInput in = new ObjectInputStream(bis)) {
            return (T) in.readObject();
        }
    }
}
