package com.linuxea.lrpc.common.compress;

public enum CompressTypeEnum {


    UNZIP((byte)1, "unzip"),



    ;
    private final byte code;
    private final String desc;

    CompressTypeEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
