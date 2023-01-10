package com.linuxea.lrpc.common.compress;

import java.util.Arrays;

public enum CompressTypeEnum {


    UNZIP((byte)1, "unzip"),



    ;
    private final byte code;
    private final String value;

    CompressTypeEnum(byte code, String value) {
        this.code = code;
        this.value = value;
    }

    public static CompressTypeEnum from(byte compressType) {
        return Arrays.stream(CompressTypeEnum.values()).filter(it -> it.getCode() == compressType).findFirst().orElse(null);
    }

    public byte getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
