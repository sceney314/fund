package com.fund.www.provider.common;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WorkerTypeEnum {
    TYPE_ONE(1, "当天分析"),
    TYPE_TOW(2, "最近 2 天汇总分析"),
    TYPE_THREE(3, "最近 3 天汇总分析"),
    TYPE_FOUR(4, "最近 4 天汇总分析"),
    TYPE_FIVE(5, "最近 5 天汇总分析"),
    TYPE_SIX(6, "最近 6 天汇总分析"),
    TYPE_SEVEN(7, "最近 7 天汇总分析"),
    TYPE_EIGHT(8, "最近 8 天汇总分析"),
    TYPE_NINE(9, "最近 9 天汇总分析"),
    TYPE_TEN(10, "最近 10 天汇总分析"),
    TYPE_ELEVEN(11, "最近 11 天汇总分析"),
    TYPE_TWELVE(12, "最近 12 天汇总分析"),
    TYPE_THIRTEEN(13, "最近 13 天汇总分析"),
    TYPE_FOURTEEN(14, "最近 14 天汇总分析"),
    TYPE_FIFTEEN(15, "最近 15 天汇总分析"),
    TYPE_GU_PIAO_CLEAR(16, "股票数据清理"),
    TYPE_GU_PIAO_DELETE(17, "股票数据清理"),
    TYPE_GU_PIAO_IMPORT(20, "股票导入"),
    TYPE_OWNER_PLATE_CHECK(21, "股票板块校验"),
    TYPE_PLATE_CHECK(22, "板块信息"),
    ;
    private final Integer code;
    private final String msg;
    private final static Map<Integer, WorkerTypeEnum> ENUM_MAP;
    private final static Map<Integer, WorkerTypeEnum> MAP_ANALYZE;
    static {
        ENUM_MAP = Arrays.stream(values()).collect(Collectors.toMap(WorkerTypeEnum::getCode, Function.identity()));
        MAP_ANALYZE = Stream.of(
                TYPE_ONE,
                TYPE_TOW,
                TYPE_THREE,
                TYPE_FOUR,
                TYPE_FIVE,
                TYPE_SIX,
                TYPE_SEVEN
        ).collect(Collectors.toMap(WorkerTypeEnum::getCode, Function.identity()));
    }

    WorkerTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static WorkerTypeEnum getInstance(Integer code){
        return ENUM_MAP.getOrDefault(code, null);
    }

    public static WorkerTypeEnum getAnalyzeInstance(Integer code){
        return MAP_ANALYZE.getOrDefault(code, null);
    }

    public static List<WorkerTypeEnum> getAnalyzeAllInstance(){
        return Collections.unmodifiableList(new ArrayList<>(MAP_ANALYZE.values()));
    }

    /**
     * 是不是分析 worker
     *
     * @param type 类型
     * @return boolean
     */
    public static boolean isAnalyzeWorker(Integer type){
        return Objects.nonNull(type) && MAP_ANALYZE.containsKey(type);
    }

}
