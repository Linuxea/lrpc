package com.linuxea.lrpc.common.compress;

import java.util.HashMap;
import java.util.Map;

public class CompressorFactoryBuilder {

    private final static Map<Byte, CompressorFactory> factoryCache = new HashMap<>() {{
        put(CompressTypeEnum.GZIP.getCode(), new GzipCompressorFactory());
    }};

    public static CompressorFactory build(byte type) {
        return factoryCache.get(type);
    }


}
