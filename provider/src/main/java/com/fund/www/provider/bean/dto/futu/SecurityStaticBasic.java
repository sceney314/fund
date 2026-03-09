package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 证券基本静态信息
 */
@Setter
@Getter
@ToString
public class SecurityStaticBasic {
    private Integer security = 1; //股票
    private Integer id = 2; //股票 ID
    private Integer lotSize = 3; //每手数量,期权类型表示一份合约的股数
    private Integer secType = 4; //Qot_Common.SecurityType,股票类型
    private Integer name = 5; //股票名字
    private Integer listTime = 6; //上市时间字符串（此字段停止维护，不建议使用，格式：yyyy-MM-dd）
    private Integer delisting = 7; //是否退市
    private Integer listTimestamp = 8; //上市时间戳（此字段停止维护，不建议使用）
    private Integer exchType = 9; //Qot_Common.ExchType,所属交易所
}
