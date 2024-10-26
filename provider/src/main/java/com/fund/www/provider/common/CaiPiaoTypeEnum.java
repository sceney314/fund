package com.fund.www.provider.common;

public enum CaiPiaoTypeEnum {
    SHUANG_SE_QIU(1, "双色球"),
    ;
    private final Integer code;
    private final String name;

    CaiPiaoTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
