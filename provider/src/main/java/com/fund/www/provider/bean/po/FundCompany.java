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
public class FundCompany extends Base{
    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 公司简称
     */
    private String shortName;

    /**
     * 公司名字
     */
    private String companyName;

    public FundCompany() {
    }

    public FundCompany(String companyCode, String shortName, String companyName) {
        this.companyCode = companyCode;
        this.shortName = shortName;
        this.companyName = companyName;
    }

    public static <K> Predicate<K> distinctPredicate(Function<? super K, Object> function){
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> null == map.putIfAbsent(function.apply(t), true);
    }
}
