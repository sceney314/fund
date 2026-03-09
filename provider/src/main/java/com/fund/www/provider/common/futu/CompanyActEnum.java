package com.fund.www.provider.common.futu;

/**
 * 公司行动
 */
public enum CompanyActEnum {
    COMPANY_ACT_NONE(0,"无"),
    COMPANY_ACT_SPLIT(1,"拆股"),
    COMPANY_ACT_JOIN(2,"合股"),
    COMPANY_ACT_BONUS(4,"送股"),
    COMPANY_ACT_TRANSFER(8,"转赠股"),
    COMPANY_ACT_ALLOT(16,"配股"),
    COMPANY_ACT_ADD(32,"增发股"),
    COMPANY_ACT_DIVIDEND(64,"现金分红"),
    COMPANY_ACT_SPDIVIDEND(128,"特别股息"),
    ;
    private final int code;
    private final String msg;

    CompanyActEnum(int code, String msg) {
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
