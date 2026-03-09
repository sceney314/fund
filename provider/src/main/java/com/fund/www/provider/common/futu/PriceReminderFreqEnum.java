package com.fund.www.provider.common.futu;

/**
 * 到价提醒频率
 */
public enum PriceReminderFreqEnum {
    PRICE_REMINDER_FREQ_UNKNOWN(0,"未知"),
    PRICE_REMINDER_FREQ_ALWAYS(1,"持续提醒"),
    PRICE_REMINDER_FREQ_ONCEADAY(2,"每日一次"),
    PRICE_REMINDER_FREQ_ONLYONCE(3,"仅提醒一次"),
    ;
    private final int code;
    private final String msg;

    PriceReminderFreqEnum(int code, String msg) {
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
