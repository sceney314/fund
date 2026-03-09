package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 基础报价的期权特有字段
 */
@Setter
@Getter
@ToString
public class OptionBasicQotExDataDTO implements Serializable {
    /**
     * 行权价
     */
    private Integer strikePrice = 1;

    /**
     * 每份合约数(整型数据)
     */
    private Integer contractSize = 2;

    /**
     * 每份合约数（浮点型数据）
     */
    private Integer contractSizeFloat = 17;

    /**
     * 未平仓合约数
     */
    private Integer openInterest = 3;

    /**
     * 隐含波动率（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）
     */
    private Integer impliedVolatility = 4;

    /**
     * 溢价（该字段为百分比字段，默认不展示 %，如 20 实际对应 20%）
     */
    private Integer premium = 5;

    /**
     * 希腊值 Delta
     */
    private Integer delta = 6;

    /**
     * 希腊值 Gamma
     */
    private Integer gamma = 7;

    /**
     * 希腊值 Vega
     */
    private Integer vega = 8;

    /**
     * 希腊值 Theta
     */
    private Integer theta = 9;

    /**
     * 希腊值 Rho
     */
    private Integer rho = 10;

    /**
     * 净未平仓合约数，仅港股期权适用
     */
    private Integer netOpenInterest = 11;

    /**
     * 距离到期日天数，负数表示已过期
     */
    private Integer expiryDateDistance = 12;

    /**
     * 合约名义金额，仅港股期权适用
     */
    private Integer contractNominalValue = 13;

    /**
     * 相等正股手数，指数期权无该字段，仅港股期权适用
     */
    private Integer ownerLotMultiplier = 14;

    /**
     * OptionAreaType，期权类型（按行权时间）
     */
    private Integer optionAreaType = 15;

    /**
     * 合约乘数
     */
    private Integer contractMultiplier = 16;

    /**
     * IndexOptionType，指数期权类型
     */
    private Integer indexOptionType = 18;
}
