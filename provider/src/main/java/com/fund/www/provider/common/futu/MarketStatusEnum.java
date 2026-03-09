package com.fund.www.provider.common.futu;

/**
 * 到价提醒市场状态
 */
public enum MarketStatusEnum {
    MARKET_STATUS_UNKNOW(0,"未知"),
    MARKET_STATUS_OPEN(1,"盘中"),
    MARKET_STATUS_USPRE(2,"美股盘前"),
    MARKET_STATUS_USAFTER(3,"美股盘后"),
    ;
    private final int code;
    private final String msg;

    MarketStatusEnum(int code, String msg) {
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
