package com.fund.www.provider.common.futu;

/**
 * 资产类别
 */
public enum AssetClassEnum {
    ASSET_CLASS_UNKNOW(0,"未知"),
    ASSET_CLASS_STOCK(1,"股票"),
    ASSET_CLASS_BOND(2,"债券"),
    ASSET_CLASS_COMMODITY(3,"商品"),
    ASSET_CLASS_CURRENCY_MARKET(4,"货币市场"),
    ASSET_CLASS_FUTURE(5,"期货"),
    ASSET_CLASS_SWAP(6,"掉期（互换）"),
    ;
    private final int code;
    private final String msg;

    AssetClassEnum(int code, String msg) {
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
