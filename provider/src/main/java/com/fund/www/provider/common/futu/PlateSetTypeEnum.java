package com.fund.www.provider.common.futu;

/**
 * 板块集合类型
 */
public enum PlateSetTypeEnum {
    PLATE_SET_TYPE_ALL(0,"所有板块"),
    PLATE_SET_TYPE_INDUSTRY(1,"行业板块"),
    PLATE_SET_TYPE_REGION(2,"地域板块,港美股市场的地域分类数据暂为空"),
    PLATE_SET_TYPE_CONCEPT(3,"概念板块"),
    PLATE_SET_TYPE_OTHER(4,"其他板块, 仅用于3207（获取股票所属板块）协议返回,不可作为其他协议的请求参数"),
    ;
    private final int code;
    private final String msg;

    PlateSetTypeEnum(int code, String msg) {
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
