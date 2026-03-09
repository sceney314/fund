package com.fund.www.provider.common.futu;

/**
 * 逐笔成交方向
 */
public enum TickerDirectionEnum {
    TICKER_DIRECTION_UNKNOWN(0,"未知"),
    TICKER_DIRECTION_BID(1,"外盘（主动买入），即以卖一价或更高的价格成交股票"),
    TICKER_DIRECTION_ASK(2,"内盘（主动卖出），即以买一价或更低的价格成交股票"),
    TICKER_DIRECTION_NEUTRAL(3,"中性盘，即以买一价与卖一价之间的价格撮合成交"),
    ;
    private final int code;
    private final String msg;

    TickerDirectionEnum(int code, String msg) {
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
