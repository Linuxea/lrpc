package com.linuxea.lrpc.common.codec;

public abstract class AbstractMessageCodecFactory {

  public abstract MessageEncode encode();


    public abstract MessageDecode decode();

}
