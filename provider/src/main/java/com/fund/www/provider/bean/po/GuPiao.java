package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class GuPiao extends Base {
    /**
     * 证券代码
     */
    private String piaoCode;

    /**
     * 证券名称
     */
    private String piaoName;

    /**
     * 信号日期
     */
    private LocalDate signalDate;

    /**
     * 策略类型
     */
    private Integer ployType;

    /**
     * 策略名称
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
     * 短线趋势
     */
    private String tendency;

}