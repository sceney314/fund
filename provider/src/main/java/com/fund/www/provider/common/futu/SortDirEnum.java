package com.fund.www.provider.common.futu;

/**
 * 排序方向
 */
public enum SortDirEnum {
    SORT_DIR_NO(0,"不排序"),
    SORT_DIR_ASCEND(1,"升序"),
    SORT_DIR_DESCEND(2,"降序"),
    ;
    private final int code;
    private final String msg;

    SortDirEnum(int code, String msg) {
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
