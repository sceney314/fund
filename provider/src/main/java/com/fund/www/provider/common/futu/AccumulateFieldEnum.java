package com.fund.www.provider.common.futu;

/**
 * 累积过滤属性
 */
public enum AccumulateFieldEnum {
    ACCUMULATE_FIELD_UNKNOWN(0,"未知"),
    ACCUMULATE_FIELD_CHANGERATE(1,"涨跌幅（精确到小数点后 3 位，超出部分会被舍弃）例如填写[-10.2,20.4]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    ACCUMULATE_FIELD_AMPLITUDE(2,"振幅（精确到小数点后 3 位，超出部分会被舍弃）例如填写[0.5,20.6]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    ACCUMULATE_FIELD_VOLUME(3,"日均成交量（精确到小数点后 0 位，超出部分会被舍弃）例如填写[2000,70000]值区间"),
    ACCUMULATE_FIELD_TURNOVER(4,"日均成交额（精确到小数点后 3 位，超出部分会被舍弃）例如填写[1400,890000]值区间"),
    ACCUMULATE_FIELD_TURNOVERRATE(5,"换手率（精确到小数点后 3 位，超出部分会被舍弃）例如填写[2,30]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    ;
    private final int code;
    private final String msg;

    AccumulateFieldEnum(int code, String msg) {
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
