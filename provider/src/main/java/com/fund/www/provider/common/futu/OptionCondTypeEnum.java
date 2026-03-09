package com.fund.www.provider.common.futu;

public enum OptionCondTypeEnum {
    OPTION_COND_TYPE_UNKNOW(0,"未知"),
    OPTION_COND_TYPE_WITHIN(1,"价内"),
    OPTION_COND_TYPE_OUTSIDE(2,"价外"),
    ;
    private final int code;
    private final String msg;

    OptionCondTypeEnum(int code, String msg) {
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
