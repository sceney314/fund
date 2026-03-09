package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 证券静态信息
 */
@Setter
@Getter
@ToString
public class SecurityStaticInfo {
    private Integer basic = 1; //证券基本静态信息
    private Integer warrantExData = 2; //窝轮额外静态信息
    private Integer optionExData = 3; //期权额外静态信息
    private Integer futureExData = 4; //期货额外静态信息
}
