package com.fund.www.provider.common.futu;

/**
 * 股票类型
 */
public enum SecurityTypeEnum {
    SECURITY_TYPE_UNKNOWN(0,"未知"),
    SECURITY_TYPE_BOND(1,"债券"),
    SECURITY_TYPE_BWRT(2,"一揽子权证"),
    SECURITY_TYPE_EQTY(3,"正股"),
    SECURITY_TYPE_TRUST(4,"信托,基金"),
    SECURITY_TYPE_WARRANT(5,"窝轮"),
    SECURITY_TYPE_INDEX(6,"指数"),
    SECURITY_TYPE_PLATE(7,"板块"),
    SECURITY_TYPE_DRVT(8,"期权"),
    SECURITY_TYPE_PLATESET(9,"板块集"),
    SECURITY_TYPE_FUTURE(10,"期货"),
    ;
    private final int code;
    private final String msg;

    SecurityTypeEnum(int code, String msg) {
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
