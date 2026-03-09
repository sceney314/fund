package com.fund.www.provider.common.futu;

/**
 * 交割周期
 */
public enum ExpirationCycleEnum {
    EXPERATION_CYCLE_UNKNOW(0,"未知"),
    EXPERATION_CYCLE_WEEK(1,"周期权"),
    EXPERATION_CYCLE_MONTH(2,"月期权"),
    ;
    private final int code;
    private final String msg;

    ExpirationCycleEnum(int code, String msg) {
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
