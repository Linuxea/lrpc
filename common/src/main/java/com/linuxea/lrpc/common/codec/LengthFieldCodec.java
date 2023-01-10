package com.linuxea.lrpc.common.codec;

public class LengthFieldCodec extends AbstractMessageCodecFactory {


    @Override
    public MessageEncode encode() {
        return new LengthFieldMessageEncode();
    }

    @Override
    public MessageDecode decode() {
        return new LengthFieldMessageDecode();
    }
}
