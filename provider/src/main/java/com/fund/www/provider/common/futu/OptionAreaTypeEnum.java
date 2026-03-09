package com.fund.www.provider.common.futu;

/**
 * 期权类型（按行权时间）
 */
public enum OptionAreaTypeEnum {
    OPTION_AREA_TYPE_UNKNOWN(0,"未知"),
    OPTION_AREA_TYPE_AMERICAN(1,"美式"),
    OPTION_AREA_TYPE_EUROPEAN(2,"欧式"),
    OPTION_AREA_TYPE_BERMUDA(3,"百慕大"),
    ;
    private final int code;
    private final String msg;

    OptionAreaTypeEnum(int code, String msg) {
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
