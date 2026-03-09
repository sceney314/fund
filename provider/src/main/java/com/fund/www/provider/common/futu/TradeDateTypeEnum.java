package com.fund.www.provider.common.futu;

/**
 * 交易日类型
 */
public enum TradeDateTypeEnum {
    TRADE_DATE_TYPE_WHOLE(0,"全天交易"),
    TRADE_DATE_TYPE_MORNING(1,"上午交易，下午休市"),
    TRADE_DATE_TYPE_AFTERNOON(2,"下午交易，上午休市"),
    ;
    private final int code;
    private final String msg;

    TradeDateTypeEnum(int code, String msg) {
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
