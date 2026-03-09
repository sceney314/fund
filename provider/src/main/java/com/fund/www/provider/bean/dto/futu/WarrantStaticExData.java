package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 窝轮额外静态信息
 */
@Setter
@Getter
@ToString
public class WarrantStaticExData {
    private Integer type = 1; //Qot_Common.WarrantType,窝轮类型
    private Integer owner = 2; //所属正股
}
