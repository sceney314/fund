package com.fund.www.provider.common.futu;

/**
 * 指数期权类别
 */
public enum IndexOptionTypeEnum {
    INDEX_OPTION_TYPE_UNKNOWN(0, "未知"),
    INDEX_OPTION_TYPE_NORMAL(1, "普通的指数期权"),
    INDEX_OPTION_TYPE_SMALL(2, "小型指数期权"),
    ;
    private final int code;
    private final String msg;

    IndexOptionTypeEnum(int code, String msg) {
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
