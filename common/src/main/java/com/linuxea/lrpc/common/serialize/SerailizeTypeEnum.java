package com.linuxea.lrpc.common.serialize;

public enum SerailizeTypeEnum {


    JDK((byte)1, "jdk"),



    ;
    private final byte code;
    private final String desc;

    SerailizeTypeEnum(byte code, String desc) {
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
