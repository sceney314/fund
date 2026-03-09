package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 基础报价
 */
@Setter
@Getter
@ToString
public class BasicQotDTO {
    private Integer security = 1; //股票
    private Integer name = 24; // 股票名称
    private Integer isSuspended = 2; //是否停牌
    private Integer listTime = 3; //上市日期字符串（此字段停止维护，不建议使用，格式：yyyy-MM-dd）
    private Integer priceSpread = 4; //价差
    private Integer updateTime = 5; //最新价的更新时间字符串（格式：yyyy-MM-dd HH:mm:ss），对其他字段不适用
    private Integer highPrice = 6; //最高价
    private Integer openPrice = 7; //开盘价
    private Integer lowPrice = 8; //最低价
    private Integer curPrice = 9; //最新价
    private Integer lastClosePrice = 10; //昨收价
    private Integer volume = 11; //成交量
    private Integer turnover = 12; //成交额
    private Integer turnoverRate = 13; //换手率（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）
    private Integer amplitude = 14; //振幅（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）
    private Integer darkStatus = 15; //DarkStatus, 暗盘交易状态
    private Integer optionExData = 16; //期权特有字段
    private Integer listTimestamp = 17; //上市日期时间戳（此字段停止维护，不建议使用）
    private Integer updateTimestamp = 18; //最新价的更新时间戳，对其他字段不适用
    private Integer preMarket = 19; //盘前数据
    private Integer afterMarket = 20; //盘后数据
    private Integer secStatus = 21; //SecurityStatus, 股票状态
    private Integer futureExData = 22; //期货特有字段
}
