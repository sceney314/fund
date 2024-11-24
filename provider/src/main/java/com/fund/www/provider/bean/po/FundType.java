package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Setter
@Getter
@ToString
public class FundType extends Base{

    /**
     * 父机构 ID
     */
    private String parentCode = "";

    /**
     * 基金类型编码
     */
    private String sinaTypeCode;

    /**
     * 基金类型名字
     */
    private String typeName;

    public static FundType getInstance(String code, String parentCode, String name){
        FundType type = new FundType();
        type.setParentCode(parentCode);
        type.setSinaTypeCode(code);
        type.setTypeName(name);
        return type;
    }

    public static <K> Predicate<K> distinctPredicate(Function<? super K, Object> function){
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> null == map.putIfAbsent(function.apply(t), true);
    }
}