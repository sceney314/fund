package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class GuPiaoAnalyzeResult extends Base {
    /**
     * 信号日期
     */
    private LocalDate signalDate;

    /**
     * 分析结果类型
     */
    private Integer analyzeType;

    /**
     * 证券代码
     */
    private String piaoCode;

    /**
     * 证券名称
     */
    private String piaoName;

    /**
     * 策略名字
     */
    private String ployName;

    /**
     * 委托价格
     */
    private String commissionPrice;

    /**
     * 目标价格
     */
    private String targetPrice;

    /**
     * 趋势结果
     */
    private String tendency;
}