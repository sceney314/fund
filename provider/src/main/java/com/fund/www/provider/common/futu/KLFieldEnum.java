package com.fund.www.provider.common.futu;

/**
 * K 线字段
 */
public enum KLFieldEnum {
    KL_FIELD_NONE(0, "未知"),
    KL_FIELD_HIGH(1, "最高价"),
    KL_FIELD_OPEN(2, "开盘价"),
    KL_FIELD_LOW(4, "最低价"),
    KL_FIELD_CLOSE(8, "收盘价"),
    KL_FIELD_LAST_CLOSE(16, "昨收价"),
    KL_FIELD_VOLUME(32, "成交量"),
    KL_FIELD_TURNOVER(64, "成交额"),
    KL_FIELD_TURNOVER_RATE(128, "换手率"),
    KL_FIELD_PE(256, "市盈率"),
    KL_FIELD_CHANGE_RATE(512, "涨跌幅"),
    ;
    private final int code;
    private final String msg;

    KLFieldEnum(int code, String msg) {
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
