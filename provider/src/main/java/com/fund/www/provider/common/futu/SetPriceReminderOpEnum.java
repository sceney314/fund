package com.fund.www.provider.common.futu;

/**
 * 设置到价提醒操作类型
 */
public enum SetPriceReminderOpEnum {
    SET_PRICE_REMINDER_OP_UNKNOWN(0,"未知"),
    SET_PRICE_REMINDER_OP_ADD(1,"新增"),
    SET_PRICE_REMINDER_OP_DEL(2,"删除"),
    SET_PRICE_REMINDER_OP_ENABLE(3,"启用"),
    SET_PRICE_REMINDER_OP_DISABLE(4,"禁用"),
    SET_PRICE_REMINDER_OP_MODIFY(5,"修改"),
    SET_PRICE_REMINDER_OP_DELALL(6,"删除全部（删除指定股票下的所有到价提醒）"),
    ;
    private final int code;
    private final String msg;

    SetPriceReminderOpEnum(int code, String msg) {
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
