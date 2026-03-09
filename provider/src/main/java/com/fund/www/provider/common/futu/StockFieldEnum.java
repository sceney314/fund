package com.fund.www.provider.common.futu;

/**
 * 简单过滤属性
 */
public enum StockFieldEnum {
    STOCK_FIELD_UNKNOWN(0,"未知"),
    STOCK_FIELD_STOCKCODE(1,"股票代码，不能填区间上下限值。"),
    STOCK_FIELD_STOCKNAME(2,"股票名称，不能填区间上下限值。"),
    STOCK_FIELD_CURPRICE(3,"最新价（精确到小数点后 3 位，超出部分会被舍弃）例如填写[10,20]值区间"),
    STOCK_FIELD_CURPRICETOHIGHEST52WEEKSRATIO(4,"(现价 - 52周最高)/52周最高，对应 PC 端离52周高点百分比（精确到小数点后 3 位，超出部分会被舍弃）例如填写[-30,-10]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    STOCK_FIELD_CURPRICETOLOWEST52WEEKSRATIO(5,"(现价 - 52周最低)/52周最低，对应 PC 端离52周低点百分比（精确到小数点后 3 位，超出部分会被舍弃）例如填写[20,40]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    STOCK_FIELD_HIGHPRICETOHIGHEST52WEEKSRATIO(6,"(今日最高 - 52周最高)/52周最高（精确到小数点后 3 位，超出部分会被舍弃）例如填写[-3,-1]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    STOCK_FIELD_LOWPRICETOLOWEST52WEEKSRATIO(7,"(今日最低 - 52周最低)/52周最低（精确到小数点后 3 位，超出部分会被舍弃）例如填写[10,70]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    STOCK_FIELD_VOLUMERATIO(8,"量比（精确到小数点后 3 位，超出部分会被舍弃）例如填写[0.5,30]值区间"),
    STOCK_FIELD_BIDASKRATIO(9,"委比（精确到小数点后 3 位，超出部分会被舍弃）例如填写[-20,80.5]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    STOCK_FIELD_LOTPRICE(10,"每手价格（精确到小数点后 3 位，超出部分会被舍弃）例如填写[40,100]值区间"),
    STOCK_FIELD_MARKETVAL(11,"市值（精确到小数点后 3 位，超出部分会被舍弃）例如填写[50000000,3000000000]值区间"),
    STOCK_FIELD_PEANNUAL(12,"市盈率(静态)（精确到小数点后 3 位，超出部分会被舍弃）例如填写[-8,65.3]值区间"),
    STOCK_FIELD_PETTM(13,"市盈率 TTM（精确到小数点后 3 位，超出部分会被舍弃）例如填写[-10,20.5]值区间"),
    STOCK_FIELD_PBRATE(14,"市净率（精确到小数点后 3 位，超出部分会被舍弃）例如填写[0.5,20]值区间"),
    STOCK_FIELD_CHANGERATE5MIN(15,"五分钟价格涨跌幅（精确到小数点后 3 位，超出部分会被舍弃）例如填写[-5,6.3]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    STOCK_FIELD_CHANGERATEBEGINYEAR(16,"年初至今价格涨跌幅（精确到小数点后 3 位，超出部分会被舍弃）例如填写[-50.1,400.7]值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),

    // 基础量价属性
    STOCK_FIELD_PSTTM(17,"市销率(TTM)（精确到小数点后 3 位，超出部分会被舍弃）例如填写 [100, 500] 值区间（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    STOCK_FIELD_PCFTTM(18,"市现率(TTM)（精确到小数点后 3 位，超出部分会被舍弃）例如填写 [100, 1000] 值区间 （该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）"),
    STOCK_FIELD_TOTALSHARE(19,"总股数（精确到小数点后 0 位，超出部分会被舍弃）例如填写 [1000000000,1000000000] 值区间 (单位：股)"),
    STOCK_FIELD_FLOATSHARE(20,"流通股数（精确到小数点后 0 位，超出部分会被舍弃）例如填写 [1000000000,1000000000] 值区间 (单位：股)"),
    STOCK_FIELD_FLOATMARKETVAL(21,"流通市值（精确到小数点后 3 位，超出部分会被舍弃）例如填写 [1000000000,1000000000] 值区间（单位：元）"),

    ;
    private final int code;
    private final String msg;

    StockFieldEnum(int code, String msg) {
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
