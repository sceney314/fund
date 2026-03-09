package com.fund.www.provider.common.futu;

/**
 * 窝轮类型
 */
public enum WarrantTypeEnum {
    WARRANT_TYPE_UNKNOWN(0,"未知"),
    WARRANT_TYPE_BUY(1,"认购窝轮"),
    WARRANT_TYPE_SELL(2,"认沽窝轮"),
    WARRANT_TYPE_BULL(3,"牛证"),
    WARRANT_TYPE_BEAR(4,"熊证"),
    WARRANT_TYPE_INLINE(5,"界内证"),
    ;
    private final int code;
    private final String msg;

    WarrantTypeEnum(int code, String msg) {
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
