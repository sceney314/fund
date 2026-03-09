package com.fund.www.provider.common.futu;

/**
 * 暗盘状态
 */
public enum DarkStatusEnum {
    DARKSTATUS_NONE(0,"无暗盘交易"),
    DARKSTATUS_TRADING(1,"暗盘交易中"),
    DARKSTATUS_END(2,"暗盘交易结束"),
    ;
    private final int code;
    private final String msg;

    DarkStatusEnum(int code, String msg) {
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
