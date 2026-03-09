package com.fund.www.provider.common.futu;

/**
 * 行情权限
 */
public enum QotRightEnum {
    QOT_RIGHT_UNKNOW(0,"未知"),
    QOT_RIGHT_BMP(1,"BMP（此权限不支持订阅）"),
    QOT_RIGHT_LEVEL1(2,"Level1"),
    QOT_RIGHT_LEVEL2(3,"Level2"),
    QOT_RIGHT_SF(4,"SF 高级行情"),
    QOT_RIGHT_NO(5,"无权限"),
    ;
    private final int code;
    private final String msg;

    QotRightEnum(int code, String msg) {
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
