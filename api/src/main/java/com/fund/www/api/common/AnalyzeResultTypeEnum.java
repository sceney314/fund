package com.fund.www.api.common;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AnalyzeResultTypeEnum {
    ANALYZE_ONE(1, "当天分析结果"),
    ANALYZE_TOW(2, "最近 2 天分析结果"),
    ANALYZE_THREE(3, "最近 3 天分析结果"),
    ANALYZE_FOUR(4, "最近 4 天分析结果"),
    ANALYZE_FIVE(5, "最近 5 天分析结果"),
    ANALYZE_SIX(6, "最近 6 天分析结果"),
    ANALYZE_SEVEN(7, "最近 7 天分析结果"),
    ANALYZE_EIGHT(8, "最近 8 天分析结果"),
    ANALYZE_NINE(9, "最近 9 天分析结果"),
    ANALYZE_TEN(10, "最近 10 天分析结果"),
    ANALYZE_ELEVEN(11, "最近 11 天分析结果"),
    ANALYZE_TWELVE(12, "最近 12 天分析结果"),
    ANALYZE_THIRTEEN(13, "最近 13 天分析结果"),
    ANALYZE_FOURTEEN(14, "最近 14 天分析结果"),
    ANALYZE_FIFTEEN(15, "最近 15 天分析结果"),
    ;
    private final Integer code;
    private final String msg;
    private final static Map<Integer, AnalyzeResultTypeEnum> MAP_ENUM;
    static {
        MAP_ENUM = Arrays.stream(values()).collect(Collectors.toMap(AnalyzeResultTypeEnum::getCode, Function.identity()));
    }

    AnalyzeResultTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static AnalyzeResultTypeEnum getInstance(Integer code){
        return MAP_ENUM.getOrDefault(code, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
