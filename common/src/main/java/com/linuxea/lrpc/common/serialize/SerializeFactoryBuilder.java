package com.linuxea.lrpc.common.serialize;

import java.util.HashMap;
import java.util.Map;

public class SerializeFactoryBuilder {

    private final static Map<Byte, SerializeFactory> factoryCache = new HashMap<>() {{
        put(SerailizeTypeEnum.JDK.getCode(), new JdkSerializeFactory());
    }};

    public static SerializeFactory build(byte type) {
        return factoryCache.get(type);
    }

}
