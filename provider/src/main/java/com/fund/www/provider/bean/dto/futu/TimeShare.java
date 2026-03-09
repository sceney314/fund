package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分时数据
 */
@Setter
@Getter
@ToString
public class TimeShare {
    private Integer time = 1; //时间字符串（格式：yyyy-MM-dd HH:mm:ss）
    private Integer minute = 2; //距离0点过了多少分钟
    private Integer isBlank = 3; //是否是空内容的点,若为 true 则只有时间信息
    private Integer price = 4; //当前价
    private Integer lastClosePrice = 5; //昨收价
    private Integer avgPrice = 6; //均价
    private Integer volume = 7; //成交量
    private Integer turnover = 8; //成交额
    private Integer timestamp = 9; //时间戳
}
