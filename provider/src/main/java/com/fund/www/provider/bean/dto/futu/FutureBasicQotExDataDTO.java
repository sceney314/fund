package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 基础报价的期货特有字段
 */
@Setter
@Getter
@ToString
public class FutureBasicQotExDataDTO {
    /**
     * 昨结
     */
    private Integer lastSettlePrice = 1;

    /**
     * 持仓量
     */
    private Integer position = 2;

    /**
     * 日增仓
     */
    private Integer positionChange = 3;

    /**
     * 距离到期日天数
     */
    private Integer expiryDateDistance = 4;
}
