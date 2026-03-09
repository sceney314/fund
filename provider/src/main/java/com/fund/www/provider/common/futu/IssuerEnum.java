package com.fund.www.provider.common.futu;

/**
 * 发行商
 */
public enum IssuerEnum {
    ISSUER_UNKNOW(0, "未知"),
    ISSUER_SG(1, "法兴"),
    ISSUER_BP(2, "法巴"),
    ISSUER_CS(3, "瑞信"),
    ISSUER_CT(4, "花旗"),
    ISSUER_EA(5, "东亚"),
    ISSUER_GS(6, "高盛"),
    ISSUER_HS(7, "汇丰"),
    ISSUER_JP(8, "摩通"),
    ISSUER_MB(9, "麦银"),
    ISSUER_SC(10, "渣打"),
    ISSUER_UB(11, "瑞银"),
    ISSUER_BI(12, "中银"),
    ISSUER_DB(13, "德银"),
    ISSUER_DC(14, "大和"),
    ISSUER_ML(15, "美林"),
    ISSUER_NM(16, "野村"),
    ISSUER_RB(17, "荷合"),
    ISSUER_RS(18, "苏皇"),
    ISSUER_BC(19, "巴克莱"),
    ISSUER_HT(20, "海通"),
    ISSUER_VT(21, "瑞通"),
    ISSUER_KC(22, "比联"),
    ISSUER_MS(23, "摩利"),
    ISSUER_GJ(24, "国君"),
    ISSUER_XZ(25, "星展"),
    ISSUER_HU(26, "华泰"),
    ISSUER_KS(27, "韩投"),
    ISSUER_CI(28, "信证"),
    ;
    private final int code;
    private final String msg;

    IssuerEnum(int code, String msg) {
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
