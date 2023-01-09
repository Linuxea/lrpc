package com.linuxea.lrpc.common.compress;

public interface Compressor {

  byte[] compress(byte[] bytes);


  byte[] decompress(byte[] bytes);

}
