package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Stock extends Base{
    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名字
     */
    private String stockName;

    /**
     * 持股比例
     */
    private String fundPercent;

    /**
     * 持股比例较上期变化
     */
    private String changePercent;

    /**
     * 持股基金数量
     */
    private String holdNum;

    /**
     * 持股基金数量变化
     */
    private String holdChangeNum;
}