package com.fund.www.provider.common.futu;

/**
 * K 线复权类型
 */
public enum RehabTypeEnum {
    REHAB_TYPE_NONE(0,"不复权"),
    REHAB_TYPE_FORWARD(1,"前复权"),
    REHAB_TYPE_BACKWARD(2,"后复权"),
    ;
    private final int code;
    private final String msg;

    RehabTypeEnum(int code, String msg) {
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
