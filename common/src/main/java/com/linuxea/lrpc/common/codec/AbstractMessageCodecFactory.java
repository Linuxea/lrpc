package com.linuxea.lrpc.common.codec;

public abstract class AbstractMessageCodecFactory {

    abstract MessageEncode encode();


    abstract MessageDecode decode();

}
