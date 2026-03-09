package com.fund.www.provider.common.futu;

/**
 * 窝轮状态
 */
public enum WarrantStatusEnum {
    WARRANT_STATUS_UNKNOW(0,"未知"),
    WARRANT_STATUS_NORMAL(1,"正常状态"),
    WARRANT_STATUS_SUSPEND(2,"停牌"),
    WARRANT_STATUS_STOPTRADE(3,"终止交易"),
    WARRANT_STATUS_PENDINGLISTING(4,"等待上市"),
    ;
    private final int code;
    private final String msg;

    WarrantStatusEnum(int code, String msg) {
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
