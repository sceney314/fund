package com.fund.www.provider.common;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WorkerStatusEnum {
    INIT(0, "初始化"),
    DOING(1, "执行中"),
    SUCCESS(2, "执行成功"),
    FAIL(3, "执行失败"),
    ;
    private final Integer code;
    private final String msg;
    private final static Map<Integer, WorkerStatusEnum> MAP_ENUM;
    static {
        MAP_ENUM = Arrays.stream(values()).collect(Collectors.toMap(WorkerStatusEnum::getCode, Function.identity()));
    }

    WorkerStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static WorkerStatusEnum getInstance(Integer code){
        return MAP_ENUM.getOrDefault(code, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
