package com.fund.www.provider.common.futu;

import com.fund.www.provider.exceptions.ServiceException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MarketEnum {
    SH_CN(21, "沪股市场", "sh_a"),
    SZ_CN(22, "深股市场", "sz_a"),
    ;
    private final int code;
    private final String msg;
    private final String sinaMarket;
    private static final Map<Integer, MarketEnum> CODE_MAP;

    static {
        CODE_MAP = Arrays.stream(values()).collect(Collectors.toMap(MarketEnum::getCode, Function.identity()));
    }

    MarketEnum(int code, String msg, String sinaMarket) {
        this.code = code;
        this.msg = msg;
        this.sinaMarket = sinaMarket;
    }

    public static MarketEnum getInstance(int code){
        MarketEnum instance = CODE_MAP.getOrDefault(code, null);
        if (Objects.isNull(instance)){
            throw new ServiceException("不存在编码[" + code + "]股票市场");
        }
        return instance;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getSinaMarket() {
        return sinaMarket;
    }
}
