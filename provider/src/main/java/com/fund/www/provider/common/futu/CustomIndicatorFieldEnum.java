package com.fund.www.provider.common.futu;

/**
 * 自定义技术指标属性
 */
public enum CustomIndicatorFieldEnum {
    CUSTOM_INDICATO_RFIELD_UNKNOWN(0, "未知"),
    CUSTOM_INDICATO_RFIELD_PRICE(1, "最新价格"),
    CUSTOM_INDICATO_RFIELD_MA5(2, "5日简单均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_MA10(3, "10日简单均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_MA20(4, "20日简单均线（不建议使用） "),
    CUSTOM_INDICATO_RFIELD_MA5_MA30(5, "30日简单均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_MA60(6, "60日简单均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_MA120(7, "120日简单均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_MA250(8, "250日简单均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_RSI(9, "RSI 指标参数的默认值为[12]"),
    CUSTOM_INDICATO_RFIELD_MA5_EMA5(10, "5日指数移动均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_EMA10(11, "10日指数移动均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_EMA20(12, "20日指数移动均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_EMA30(13, "30日指数移动均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_EMA60(14, "60日指数移动均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_EMA120(15, "120日指数移动均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_EMA250(16, "250日指数移动均线（不建议使用）"),
    CUSTOM_INDICATO_RFIELD_MA5_VALUE(17, "自定义数值（STOCK_FIELD1不支持此字段）"),
    CUSTOM_INDICATO_RFIELD_MA5_MA(30, "简单均线"),
    CUSTOM_INDICATO_RFIELD_MA5_EMA(40, "指数移动均线"),
    CUSTOM_INDICATO_RFIELD_MA5_KDJ_K(50, "KDJ 指标的 K 值。指标参数需要根据 KDJ 进行传参。不传则默认为 [9,3,3]"),
    CUSTOM_INDICATO_RFIELD_MA5_KDJ_D(51, "KDJ 指标的 D 值。指标参数需要根据 KDJ 进行传参。不传则默认为 [9,3,3]"),
    CUSTOM_INDICATO_RFIELD_MA5_KDJ_J(52, "KDJ 指标的 J 值。指标参数需要根据 KDJ 进行传参。不传则默认为 [9,3,3]"),
    CUSTOM_INDICATO_RFIELD_MA5_MACD_DIFF(60, "MACD 指标的 DIFF 值。指标参数需要根据 MACD 进行传参。不传则默认为 [12,26,9]"),
    CUSTOM_INDICATO_RFIELD_MA5_MACD_DEA(61, "MACD 指标的 DEA 值。指标参数需要根据 MACD 进行传参。不传则默认为 [12,26,9]"),
    CUSTOM_INDICATO_RFIELD_MA5_MACD(62, "MACD 指标的 MACD 值。指标参数需要根据 MACD 进行传参。不传则默认为 [12,26,9]"),
    CUSTOM_INDICATO_RFIELD_MA5_BOLL_UPPER(70, "BOLL 指标的 UPPER 值。指标参数需要根据 BOLL 进行传参。不传则默认为 [20,2]"),
    CUSTOM_INDICATO_RFIELD_MA5_BOLL_MIDDLER(71, "BOLL 指标的 MIDDLER 值。指标参数需要根据 BOLL 进行传参。不传则默认为 [20,2]"),
    CUSTOM_INDICATO_RFIELD_MA5_BOLL_LOWER(72, "BOLL 指标的 LOWER 值。指标参数需要根据 BOLL 进行传参。不传则默认为 [20,2]"),
    ;
    private final int code;
    private final String msg;

    CustomIndicatorFieldEnum(int code, String msg) {
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
