package com.fund.www.provider.common.futu;

/**
 * 行情市场
 */
public enum QotMarketEnum {
    QOT_MARKET_UNKNOWN(0,"未知市场"),
    QOT_MARKET_HK_SECURITY(1,"香港市场"),
    QOT_MARKET_HK_FUTURE(2,"港期货（已废弃，使用 QOT_MARKET_HK_Security 即可）"),
    QOT_MARKET_US_SECURITY(11,"美国市场"),
    QOT_MARKET_CNSH_SECURITY(21,"沪股市场"),
    QOT_MARKET_CNSZ_SECURITY(22,"深股市场"),
    QOT_MARKET_SG_SECURITY(31,"新加坡市场"),
    QOT_MARKET_JP_SECURITY(41,"日本市场"),
    QOT_MARKET_AU_SECURITY(51,"澳大利亚市场"),
    QOT_MARKET_MY_SECURITY(61,"马来西亚市场"),
    QOT_MARKET_CA_SECURITY(71,"加拿大市场"),
    QOT_MARKET_FX_SECURITY(81,"外汇市场"),
    ;
    private final int code;
    private final String msg;

    QotMarketEnum(int code, String msg) {
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
