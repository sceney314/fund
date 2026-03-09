package com.fund.www.provider.common.futu;

/**
 * 逐笔推送类型
 */
public enum PushDataTypeEnum {
    PUSH_DATA_TYPE_UNKNOW(0,"未知"),
    PUSH_DATA_TYPE_REALTIME(1,"实时推送的数据"),
    PUSH_DATA_TYPE_BYDISCONN(2,"对后台行情连接断开期间拉取补充的数据（最多50个）"),
    PUSH_DATA_TYPE_CACHE(3,"非实时非连接断开补充数据"),
    ;
    private final int code;
    private final String msg;

    PushDataTypeEnum(int code, String msg) {
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
