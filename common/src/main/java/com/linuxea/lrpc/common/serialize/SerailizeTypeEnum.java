package com.linuxea.lrpc.common.serialize;

import java.util.Arrays;

public enum SerailizeTypeEnum {


    JDK((byte)1, "jdk"),



    ;
    private final byte code;
    private final String value;

    SerailizeTypeEnum(byte code, String value) {
        this.code = code;
        this.value = value;
    }

    public static SerailizeTypeEnum from(byte serializeType) {
        return Arrays.stream(SerailizeTypeEnum.values()).filter(it -> it.getCode() == serializeType).findFirst().orElse(null);
    }

    public byte getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
