package com.fund.www.provider.common.futu;

/**
 * 市场状态
 */
public enum QotMarketStateEnum {
    QOT_MARKET_STATE_NONE(0,"无交易"),
    QOT_MARKET_STATE_AUCTION(1,"盘前竞价"),
    QOT_MARKET_STATE_WAITINGOPEN(2,"等待开盘"),
    QOT_MARKET_STATE_MORNING(3,"早盘"),
    QOT_MARKET_STATE_REST(4,"午间休市"),
    QOT_MARKET_STATE_AFTERNOON(5,"午盘 / 美股持续交易时段"),
    QOT_MARKET_STATE_CLOSED(6,"收盘"),
    QOT_MARKET_STATE_PREMARKETBEGIN(8,"美股盘前交易时段"),
    QOT_MARKET_STATE_PREMARKETEND(9,"美股盘前交易结束"),
    QOT_MARKET_STATE_AFTERHOURSBEGIN(10,"美股盘后交易时段"),
    QOT_MARKET_STATE_AFTERHOURSEND(11,"美股收盘"),
    QOT_MARKET_STATE_NIGHTOPEN(13,"夜市交易时段"),
    QOT_MARKET_STATE_NIGHTEND(14,"夜市收盘"),
    QOT_MARKET_STATE_FUTUREDAYOPEN(15,"日市交易时段"),
    QOT_MARKET_STATE_FUTUREDAYBREAK(16,"日市休市"),
    QOT_MARKET_STATE_FUTUREDAYCLOSE(17,"日市收盘"),
    QOT_MARKET_STATE_FUTUREDAYWAITFOROPEN(18,"期货待开盘"),
    QOT_MARKET_STATE_HKCAS(19,"盘后竞价,港股市场增加 CAS 机制对应的市场状态"),
    QOT_MARKET_STATE_FUTURENIGHTWAIT(20,"夜市等待开盘（已废弃）"),
    QOT_MARKET_STATE_FUTUREAFTERNOON(21,"期货下午开盘（已废弃）"),
    QOT_MARKET_STATE_FUTURESWITCHDATE(22,"美期待开盘"),
    QOT_MARKET_STATE_FUTUREOPEN(23,"美期交易时段"),
    QOT_MARKET_STATE_FUTUREBREAK(24,"美期中盘休息"),
    QOT_MARKET_STATE_FUTUREBREAKOVER(25,"美期休息后交易时段"),
    QOT_MARKET_STATE_FUTURECLOSE(26,"美期收盘"),
    QOT_MARKET_STATE_STIBAFTERHOURSWAIT(27,"科创板的盘后撮合时段（已废弃）"),
    QOT_MARKET_STATE_STIBAFTERHOURSBEGIN(28,"科创板的盘后交易开始（已废弃）"),
    QOT_MARKET_STATE_STIBAFTERHOURSEND(29,"科创板的盘后交易结束（已废弃）"),
    QOT_MARKET_STATE_NIGHT(32,"美指期权夜市交易时段"),
    QOT_MARKET_STATE_TRADE_AT_LAST(35,"美指期权盘尾交易时段"),
    ;
    private final int code;
    private final String msg;

    QotMarketStateEnum(int code, String msg) {
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
