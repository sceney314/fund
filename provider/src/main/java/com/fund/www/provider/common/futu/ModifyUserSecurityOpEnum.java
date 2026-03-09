package com.fund.www.provider.common.futu;

/**
 * 自选股操作
 */
public enum ModifyUserSecurityOpEnum {
    MODIFY_USER_SECURITY_OP_UNKNOWN(0,"未知"),
    MODIFY_USER_SECURITY_OP_ADD(1,"新增"),
    MODIFY_USER_SECURITY_OP_DEL(2,"删除自选"),
    MODIFY_USER_SECURITY_OP_MOVE_OUT(3,"移出分组"),
    ;
    private final int code;
    private final String msg;

    ModifyUserSecurityOpEnum(int code, String msg) {
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
