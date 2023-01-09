package com.linuxea.lrpc.common.serialize;

import java.util.HashMap;
import java.util.Map;

public class SerializeFactoryBuilder {

    private final static Map<String, SerializeFactory> factoryCache = new HashMap<>() {{
        put("jdk", new JdkSerializeFactory());
    }};

    public static SerializeFactory build(String type) {
        return factoryCache.get(type.toLowerCase());
    }

}
