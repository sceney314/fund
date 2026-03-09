package com.fund.www.provider.common.futu;

/**
 * 自选股分组类型
 */
public enum GroupTypeEnum {
    GROUP_TYPE_UNKNOWN(0, "未知"),
    GROUP_TYPE_CUSTOM(1, "自定义分组"),
    GROUP_TYPE_SYSTEM(2, "系统分组"),
    GROUP_TYPE_ALL(3, "全部分组"),
    ;
    private final int code;
    private final String msg;

    GroupTypeEnum(int code, String msg) {
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
