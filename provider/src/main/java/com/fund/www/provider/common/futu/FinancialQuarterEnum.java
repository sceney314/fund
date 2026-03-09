package com.fund.www.provider.common.futu;

/**
 * 财务过滤属性周期
 */
public enum FinancialQuarterEnum {
    FINANCIAL_QUARTER_UNKNOWN(0, "未知"),
    FINANCIAL_QUARTER_ANNUAL(1, "年报"),
    FINANCIAL_QUARTER_FIRST_QUARTER(2, "一季报"),
    FINANCIAL_QUARTER_INTERIM(3, "中报"),
    FINANCIAL_QUARTER_THIRD_QUARTER(4, "三季报"),
    FINANCIAL_QUARTER_MOST_RECENT_QUARTER(5, "最近季报"),
    ;
    private final int code;
    private final String msg;

    FinancialQuarterEnum(int code, String msg) {
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
