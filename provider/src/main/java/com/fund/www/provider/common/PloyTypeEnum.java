package com.fund.www.provider.common;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PloyTypeEnum {
    PLOY_ONE(1, "1号策略"),
    PLOY_89K(89, "89K策略"),
    ;
    private final Integer code;
    private final String msg;
    private final static Map<Integer, PloyTypeEnum> MAP_ANALYZE;
    static {
        MAP_ANALYZE = Arrays.stream(values()).collect(Collectors.toMap(PloyTypeEnum::getCode, Function.identity()));
    }

    PloyTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static PloyTypeEnum getInstance(Integer code){
        return MAP_ANALYZE.getOrDefault(code, null);
    }

    public static List<PloyTypeEnum> getAllInstance(){
        return Collections.unmodifiableList(new ArrayList<>(MAP_ANALYZE.values()));
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
