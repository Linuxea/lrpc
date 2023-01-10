package com.linuxea.lrpc.common.compress;

public interface CompressorFactory {

  byte[] compress(byte[] bytes);


  byte[] decompress(byte[] bytes);

}
