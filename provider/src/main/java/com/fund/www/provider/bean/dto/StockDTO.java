package com.fund.www.provider.bean.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class StockDTO implements Serializable {
    /**
     * 股票编码
     */
    private String stockCode;

    /**
     * 股票明细
     */
    private String stockName;

    /**
     * 最新价
     */
    private String price;

    /**
     * 涨跌幅
     */
    private String scope;

    /**
     * 持股比例
     */
    private String percent;

    /**
     * 较上期变化
     */
    private String changePercent;

    /**
     * 持股基金数量
     */
    private String stockNum;

    /**
     * 较上期变化
     */
    private String numChange;
}
