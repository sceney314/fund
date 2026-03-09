package com.fund.www.provider.common.futu;

/**
 * 交易日查询市场
 */
public enum TradeDateMarketEnum {
    TRADE_DATE_MARKET_UNKNOWN(0,"未知"),
    TRADE_DATE_MARKET_HK(1,"香港市场（含股票、ETFs、窝轮、牛熊、期权、非假期交易期货；不含假期交易期货）"),
    TRADE_DATE_MARKET_US(2,"美国市场（含股票、ETFs、期权；不含期货）"),
    TRADE_DATE_MARKET_CN(3,"A 股市场"),
    TRADE_DATE_MARKET_NT(4,"深（沪）股通"),
    TRADE_DATE_MARKET_ST(5,"港股通（深、沪）"),
    TRADE_DATE_MARKET_JP_FUTURE(6,"日本期货"),
    TRADE_DATE_MARKET_SG_FUTURE(7,"新加坡期货"),
    ;
    private final int code;
    private final String msg;

    TradeDateMarketEnum(int code, String msg) {
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
