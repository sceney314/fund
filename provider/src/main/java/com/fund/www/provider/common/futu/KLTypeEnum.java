package com.fund.www.provider.common.futu;

/**
 * K 线类型
 */
public enum KLTypeEnum {
    KL_TYPE_UNKNOWN(0, "未知"),
    KL_TYPE_1MIN(1, "1分 K 线"),
    KL_TYPE_DAY(2, "日 K 线"),
    KL_TYPE_WEEK(3, "周 K 线(期权暂不支持)"),
    KL_TYPE_MONTH(4, "月 K 线(期权暂不支持)"),
    KL_TYPE_YEAR(5, "年 K 线(期权暂不支持)"),
    KL_TYPE_5MIN(6, "5分 K 线"),
    KL_TYPE_15MIN(7, "15分 K 线"),
    KL_TYPE_30MIN(8, "30分 K 线(期权暂不支持)"),
    KL_TYPE_60MIN(9, "60分 K 线"),
    KL_TYPE_3MIN(10, "3分 K 线(期权暂不支持)"),
    KL_TYPE_QUARTER(11, "季 K 线(期权暂不支持)"),
    ;
    private final int code;
    private final String msg;

    KLTypeEnum(int code, String msg) {
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
