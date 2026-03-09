package com.fund.www.provider.common.futu;

/**
 * 窝轮价内/外
 */
public enum PriceTypeEnum {
    PRICE_TYPE_UNKNOW(0,"未知"),
    PRICE_TYPE_OUTSIDE(1,"价外，界内证表示界外"),
    PRICE_TYPE_WITHIN(2,"价内，界内证表示界内"),
    ;
    private final int code;
    private final String msg;

    PriceTypeEnum(int code, String msg) {
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
