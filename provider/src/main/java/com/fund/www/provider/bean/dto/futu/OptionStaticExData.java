package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 期权额外静态信息
 */
@Setter
@Getter
@ToString
public class OptionStaticExData {
    private Integer type = 1; //Qot_Common.OptionType,期权
    private Integer owner = 2; //标的股
    private Integer strikeTime = 3; //行权日（格式：yyyy-MM-dd）
    private Integer strikePrice = 4; //行权价
    private Integer suspend = 5; //是否停牌
    private Integer market = 6; //发行市场名字
    private Integer strikeTimestamp = 7; //行权日时间戳
    private Integer indexOptionType = 8; //Qot_Common.IndexOptionType, 指数期权的类型，仅在指数期权有效
}
