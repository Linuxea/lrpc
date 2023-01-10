package com.linuxea.lrpc.common.codec;

import com.linuxea.lrpc.common.compress.CompressTypeEnum;
import com.linuxea.lrpc.common.compress.CompressorFactoryBuilder;
import com.linuxea.lrpc.common.model.RpcMessage;
import com.linuxea.lrpc.common.serialize.SerailizeTypeEnum;
import com.linuxea.lrpc.common.serialize.SerializeFactoryBuilder;

import java.nio.ByteBuffer;

public class LengthFieldMessageEncode implements MessageEncode {


    @Override
    public byte[] encode(RpcMessage obj) throws Exception {
        String serialize = obj.getSerialize();
        byte[] serializeBytes = SerializeFactoryBuilder.build(serialize).serialize(obj);

        String compress = obj.getCompress();
        byte[] compressBytes = CompressorFactoryBuilder.build(compress).compress(serializeBytes);

        // body and it's length
        int bodyLength = compressBytes.length;
        int fullLength = 12 + bodyLength; // four bytes for full length field
        ByteBuffer byteBuffer = ByteBuffer.allocate(fullLength);
        byteBuffer.put(CodecConstant.MAGIC_NUM).put((byte) 1).putInt(fullLength).put((byte) 1).put(SerailizeTypeEnum.JDK.getCode()).put(CompressTypeEnum.UNZIP.getCode()).put(compressBytes);
        return byteBuffer.array();
    }
}
