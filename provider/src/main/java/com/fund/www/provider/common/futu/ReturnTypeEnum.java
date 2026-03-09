package com.fund.www.provider.common.futu;

public enum ReturnTypeEnum {
    RETURN_TYPE_SUCCEED(0,"成功"),
    RETURN_TYPE_FAILED(-1,"失败"),
    RETURN_TYPE_TIMEOUT(-100,"超时"),
    RETURN_TYPE_UNKNOWN(-400,"未知结果"),
    ;
    private final int code;
    private final String msg;

    ReturnTypeEnum(int code, String msg) {
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
