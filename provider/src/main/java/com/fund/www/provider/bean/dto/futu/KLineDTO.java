package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * K 线数据
 */
@Setter
@Getter
@ToString
public class KLineDTO implements Serializable {
    /**
     * 时间戳字符串（格式：yyyy-MM-dd HH:mm:ss）
     */
    private Integer time = 1;

    /**
     * 是否是空内容的点,若为 true 则只有时间信息
     */
    private Integer isBlank = 2;

    /**
     * 最高价
     */
    private Integer highPrice = 3;

    /**
     * 开盘价
     */
    private Integer openPrice = 4;

    /**
     * 最低价
     */
    private Integer lowPrice = 5;

    /**
     * 收盘价
     */
    private Integer closePrice = 6;

    /**
     * 昨收价
     */
    private Integer lastClosePrice = 7;

    /**
     * 成交量
     */
    private Integer volume = 8;

    /**
     * 成交额
     */
    private Integer turnover = 9;

    /**
     * 换手率（该字段为百分比字段，展示为小数表示）
     */
    private Integer turnoverRate = 10;

    /**
     * 市盈率
     */
    private Integer pe = 11;

    /**
     * 涨跌幅（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）
     */
    private Integer changeRate = 12;

    /**
     * 时间戳
     */
    private Integer timestamp = 13;
}
