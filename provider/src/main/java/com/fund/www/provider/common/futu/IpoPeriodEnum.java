package com.fund.www.provider.common.futu;

/**
 * 上市时段
 */
public enum IpoPeriodEnum {
    IPOPERIOD_UNKNOW(0, "未知"),
    IPOPERIOD_TODAY(1, "今日上市"),
    IPOPERIOD_TOMORROW(2, "明日上市"),
    IPOPERIOD_NEXTWEEK(3, "未来一周上市"),
    IPOPERIOD_LASTWEEK(4, "过去一周上市"),
    IPOPERIOD_LASTMONTH(5, "过去一月上市"),
    ;
    private final int code;
    private final String msg;

    IpoPeriodEnum(int code, String msg) {
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
