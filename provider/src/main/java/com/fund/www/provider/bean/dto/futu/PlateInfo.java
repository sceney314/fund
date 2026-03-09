package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 板块信息
 */
@Setter
@Getter
@ToString
public class PlateInfo {
    private Integer plate = 1; //板块
    private Integer name = 2; //板块名字
    private Integer plateType = 3; //PlateSetType 板块类型, 仅3207（获取股票所属板块）协议返回该字段
}
