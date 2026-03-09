package com.fund.www.provider.common.futu;

/**
 * 周期类型
 */
public enum PeriodTypeEnum {
    PERIOD_TYPE_INTRADAY(0,"实时"),
    PERIOD_TYPE_DAY(1,"日"),
    PERIOD_TYPE_WEEK(2,"周"),
    PERIOD_TYPE_MONTH(3,"月"),
    ;
    private final int code;
    private final String msg;

    PeriodTypeEnum(int code, String msg) {
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
