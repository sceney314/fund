package com.fund.www.provider.common.futu;

/**
 * 所属交易所
 */
public enum ExchTypeEnum {
    EXCH_TYPE_UNKNOWN(0,"未知"),
    EXCH_TYPE_HK_MAINBOARD(1,"港交所·主板"),
    EXCH_TYPE_HK_GEMBOARD(2,"港交所·创业板"),
    EXCH_TYPE_HK_HKEX(3,"港交所"),
    EXCH_TYPE_US_NYSE(4,"纽交所"),
    EXCH_TYPE_US_NASDAQ(5,"纳斯达克"),
    EXCH_TYPE_US_PINK(6,"OTC 市场"),
    EXCH_TYPE_US_AMEX(7,"美交所"),
    EXCH_TYPE_US_OPTION(8,"美国（仅美股期权适用）"),
    EXCH_TYPE_US_NYMEX(9,"NYMEX"),
    EXCH_TYPE_US_COMEX(10,"COMEX"),
    EXCH_TYPE_US_CBOT(11,"CBOT"),
    EXCH_TYPE_US_CME(12,"CME"),
    EXCH_TYPE_US_CBOE(13,"CBOE"),
    EXCH_TYPE_CN_SH(14,"上交所"),
    EXCH_TYPE_CN_SZ(15,"深交所"),
    EXCH_TYPE_CN_STIB(16,"科创板"),
    EXCH_TYPE_SG_SGX(17,"新交所"),
    EXCH_TYPE_JP_OSE(18,"大阪交易所"),

    ;
    private final int code;
    private final String msg;

    ExchTypeEnum(int code, String msg) {
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
