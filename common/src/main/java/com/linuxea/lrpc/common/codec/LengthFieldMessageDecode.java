package com.linuxea.lrpc.common.codec;

import com.linuxea.lrpc.common.compress.CompressorFactoryBuilder;
import com.linuxea.lrpc.common.model.RpcMessage;
import com.linuxea.lrpc.common.serialize.SerializeFactoryBuilder;

import java.io.InputStream;
import java.nio.ByteBuffer;

public class LengthFieldMessageDecode implements MessageDecode {

    @Override
    public RpcMessage decode(InputStream inputStream) throws Exception {

        byte[] magicNum = inputStream.readNBytes(4);
        byte[] version = inputStream.readNBytes(1);
        byte[] fullLength = inputStream.readNBytes(4);
        byte[] messageType = inputStream.readNBytes(1);
        byte[] serializeType = inputStream.readNBytes(1);
        byte[] compressType = inputStream.readNBytes(1);
        // extract data bytes
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(fullLength);
        buffer.rewind();
        int value = buffer.getInt();
        byte[] dataBytes = inputStream.readNBytes(value - 12);
        // uncompress
        byte[] decompress = CompressorFactoryBuilder.build(compressType[0]).decompress(dataBytes);
        // deserialize
        return SerializeFactoryBuilder.build(serializeType[0]).deserialize(decompress, RpcMessage.class);
    }
}
