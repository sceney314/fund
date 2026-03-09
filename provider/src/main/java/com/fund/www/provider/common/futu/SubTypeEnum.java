package com.fund.www.provider.common.futu;

/**
 * 订阅类型
 */
public enum SubTypeEnum {
    SUB_TYPE_NONE(0,"未知"),
    SUB_TYPE_BASIC(1,"基础报价"),
    SUB_TYPE_ORDERBOOK(2,"摆盘"),
    SUB_TYPE_TICKER(4,"逐笔"),
    SUB_TYPE_RT(5,"分时"),
    SUB_TYPE_KL_DAY(6,"日 K"),
    SUB_TYPE_KL_5MIN(7,"5分 K"),
    SUB_TYPE_KL_15MIN(8,"15分 K"),
    SUB_TYPE_KL_30MIN(9,"30分 K"),
    SUB_TYPE_KL_60MIN(10,"60分 K"),
    SUB_TYPE_KL_1MIN(11,"1分 K"),
    SUB_TYPE_KL_WEEK(12,"周 K"),
    SUB_TYPE_KL_MONTH(13,"月 K"),
    SUB_TYPE_BROKER(14,"经纪队列"),
    SUB_TYPE_KL_QURATER(15,"季 K"),
    SUB_TYPE_KL_YEAR(16,"年 K"),
    SUB_TYPE_KL_3MIN(17,"3分 K"),
    ;
    private final int code;
    private final String msg;

    SubTypeEnum(int code, String msg) {
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
