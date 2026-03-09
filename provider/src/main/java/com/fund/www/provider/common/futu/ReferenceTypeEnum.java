package com.fund.www.provider.common.futu;

/**
 * 关联数据类型
 */
public enum ReferenceTypeEnum {
    REFERENCE_TYPE_UNKNOW(0,"未知"),
    REFERENCE_TYPE_WARRANT(1,"正股相关的窝轮"),
    REFERENCE_TYPE_FUTURE(2,"期货主连的相关合约"),
    ;
    private final int code;
    private final String msg;

    ReferenceTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
