package com.fund.www.provider.common.futu;

public enum OptionTypeEnum {
    OPTION_TYPE_UNKNOWN(0,"未知"),
    OPTION_TYPE_CALL(1,"看涨期权"),
    OPTION_TYPE_PUT(2,"看跌期权"),
    ;
    private final int code;
    private final String msg;

    OptionTypeEnum(int code, String msg) {
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
