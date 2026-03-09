package com.fund.www.provider.common.futu;

/**
 * 股票状态
 */
public enum SecurityStatusEnum {
    SECURITY_STATUS_UNKNOWN(0,"未知"),
    SECURITY_STATUS_NORMAL(1,"正常状态"),
    SECURITY_STATUS_LISTING(2,"待上市"),
    SECURITY_STATUS_PURCHASING(3,"申购中"),
    SECURITY_STATUS_SUBSCRIBING(4,"认购中"),
    SECURITY_STATUS_BEFOREDRAKTRADEOPENING(5,"暗盘开盘前"),
    SECURITY_STATUS_DRAKTRADING(6,"暗盘交易中"),
    SECURITY_STATUS_DRAKTRADEEND(7,"暗盘已收盘"),
    SECURITY_STATUS_TOBEOPEN(8,"待开盘"),
    SECURITY_STATUS_SUSPENDED(9,"停牌"),
    SECURITY_STATUS_CALLED(10,"已收回"),
    SECURITY_STATUS_EXPIREDLASTTRADINGDATE(11,"已过最后交易日"),
    SECURITY_STATUS_EXPIRED(12,"已过期"),
    SECURITY_STATUS_DELISTED(13,"已退市"),
    SECURITY_STATUS_CHANGETOTEMPORARYCODE(14,"公司行动中，交易关闭，转至临时代码交易"),
    SECURITY_STATUS_TEMPORARYCODETRADEEND(15,"临时买卖结束，交易关闭"),
    SECURITY_STATUS_CHANGEDPLATETRADEEND(16,"已转板，旧代码交易关闭"),
    SECURITY_STATUS_CHANGEDCODETRADEEND(17,"已换代码，旧代码交易关闭"),
    SECURITY_STATUS_RECOVERABLECIRCUITBREAKER(18,"可恢复性熔断"),
    SECURITY_STATUS_UNRECOVERABLECIRCUITBREAKER(19,"不可恢复性熔断"),
    SECURITY_STATUS_AFTERCOMBINATION(20,"盘后撮合"),
    SECURITY_STATUS_AFTERTRANSATION(21,"盘后交易"),
    ;
    private final int code;
    private final String msg;

    SecurityStatusEnum(int code, String msg) {
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
