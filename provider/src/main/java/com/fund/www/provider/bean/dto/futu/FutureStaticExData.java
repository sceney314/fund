package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 期货额外静态信息
 */
@Setter
@Getter
@ToString
public class FutureStaticExData {
    private Integer lastTradeTime = 1; //最后交易日，只有非主连期货合约才有该字段
    private Integer lastTradeTimestamp = 2; //最后交易日时间戳，只有非主连期货合约才有该字段
    private Integer isMainContract = 3; //是否主连合约
}
