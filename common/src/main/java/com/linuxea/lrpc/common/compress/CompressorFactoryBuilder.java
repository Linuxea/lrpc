package com.linuxea.lrpc.common.compress;

import java.util.HashMap;
import java.util.Map;

public class CompressorFactoryBuilder {

    private final static Map<String, CompressorFactory> factoryCache = new HashMap<>() {{
        put("gzip", new GzipCompressorFactory());
    }};

    public static CompressorFactory build(String type) {
        return factoryCache.get(type.toLowerCase());
    }


}
