package com.fund.www.provider.common.futu;

/**
 * 到价提醒类型
 */
public enum PriceReminderTypeEnum {
    PRICE_REMINDER_TYPE_UNKNOWN(0,"未知"),
    PRICE_REMINDER_TYPE_PRICEUP(1,"价格涨到"),
    PRICE_REMINDER_TYPE_PRICEDOWN(2,"价格跌到"),
    PRICE_REMINDER_TYPE_CHANGERATEUP(3,"日涨幅超（该字段为百分比字段，设置时填 20 表示 20%）"),
    PRICE_REMINDER_TYPE_CHANGERATEDOWN(4,"日跌幅超（该字段为百分比字段，设置时填 20 表示 20%）"),
    PRICE_REMINDER_TYPE_5MINCHANGERATEUP(5,"5 分钟涨幅超（该字段为百分比字段，设置时填 20 表示 20%）"),
    PRICE_REMINDER_TYPE_5MINCHANGERATEDOWN(6,"5 分钟跌幅超（该字段为百分比字段，设置时填 20 表示 20%）"),
    PRICE_REMINDER_TYPE_VOLUMEUP(7,"成交量超过"),
    PRICE_REMINDER_TYPE_TURNOVERUP(8,"成交额超过"),
    PRICE_REMINDER_TYPE_TURNOVERRATEUP(9,"换手率超过（该字段为百分比字段，设置时填 20 表示 20%）"),
    PRICE_REMINDER_TYPE_BIDPRICEUP(10,"买一价高于"),
    PRICE_REMINDER_TYPE_ASKPRICEDOWN(11,"卖一价低于"),
    PRICE_REMINDER_TYPE_BIDVOLUP(12,"买一量高于"),
    PRICE_REMINDER_TYPE_ASKVOLUP(13,"卖一量高于"),
    PRICE_REMINDER_TYPE_3MINCHANGERATEUP(14,"3 分钟涨幅超（该字段为百分比字段，设置时填 20 表示 20%）"),
    PRICE_REMINDER_TYPE_3MINCHANGERATEDOWN(15,"3 分钟跌幅超（该字段为百分比字段，设置时填 20 表示 20%）"),
    ;
    private final int code;
    private final String msg;

    PriceReminderTypeEnum(int code, String msg) {
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
