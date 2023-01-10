package com.linuxea.lrpc.common.codec;

import com.linuxea.lrpc.common.compress.CompressTypeEnum;
import com.linuxea.lrpc.common.compress.CompressorFactoryBuilder;
import com.linuxea.lrpc.common.model.RpcMessage;
import com.linuxea.lrpc.common.serialize.SerailizeTypeEnum;
import com.linuxea.lrpc.common.serialize.SerializeFactoryBuilder;

import java.nio.ByteBuffer;

public class LengthFieldMessageDecode implements MessageDecode {

    @Override
    public RpcMessage decode(byte[] bytes) throws Exception {

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int magicNum = byteBuffer.getInt();
        byte version = byteBuffer.get();
        int fullLength = byteBuffer.getInt();
        byte messageType = byteBuffer.get();
        byte serializeType = byteBuffer.get();
        byte compressType = byteBuffer.get();
        // extract data bytes
        byte[] dataBytes = ByteBuffer.allocate(fullLength - 12).get(bytes, 12, fullLength - 12).array();
        // uncompress
        byte[] decompress = CompressorFactoryBuilder.build(CompressTypeEnum.from(compressType).getValue()).decompress(dataBytes);
        // deserialize
        return SerializeFactoryBuilder.build(SerailizeTypeEnum.from(serializeType).getValue()).deserialize(decompress, RpcMessage.class);
    }
}
