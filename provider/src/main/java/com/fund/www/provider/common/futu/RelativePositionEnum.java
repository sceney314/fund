package com.fund.www.provider.common.futu;

/**
 * 相对位置
 */
public enum RelativePositionEnum {
    RELATIVE_POSITION_UNKNOWN(0, "未知"),
    RELATIVE_POSITION_MORE(1, "大于，FIRST位于SECOND的上方"),
    RELATIVE_POSITION_LESS(2, "小于，FIRST位于SECOND的下方"),
    RELATIVE_POSITION_CROSSUP(3, "升穿，FIRST从下往上穿SECOND"),
    RELATIVE_POSITION_CROSSDOWN(4, "跌穿，FIRST从上往下穿SECOND"),
    ;
    private final int code;
    private final String msg;

    RelativePositionEnum(int code, String msg) {
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
