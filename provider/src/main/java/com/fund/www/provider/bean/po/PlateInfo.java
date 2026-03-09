package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Setter
@Getter
@ToString
public class PlateInfo extends Base{
    /**
     * 板块编码
     */
    private String plateCode;

    /**
     * 板块名字
     */
    private String plateName;

    public PlateInfo() {
    }

    public PlateInfo(String plateCode, String plateName) {
        this.plateCode = plateCode;
        this.plateName = plateName;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> key){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(key.apply(t), Boolean.TRUE) == null;
    }
}