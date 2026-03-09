package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 盘前盘后数据
 */
@Setter
@Getter
@ToString
public class PreAfterMarketData {
    private Integer price = 1;  // 盘前或盘后## 价格
    private Integer highPrice = 2;  // 盘前或盘后## 最高价
    private Integer lowPrice = 3;  // 盘前或盘后## 最低价
    private Integer volume = 4;  // 盘前或盘后## 成交量
    private Integer turnover = 5;  // 盘前或盘后## 成交额
    private Integer changeVal = 6;  // 盘前或盘后## 涨跌额
    private Integer changeRate = 7;  // 盘前或盘后## 涨跌幅（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）
    private Integer amplitude = 8;  // 盘前或盘后## 振幅（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）
}
