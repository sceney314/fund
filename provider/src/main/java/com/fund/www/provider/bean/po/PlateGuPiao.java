package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PlateGuPiao extends Base{

    /**
     * 股票编码
     */
    private String gupiaoCode;

    /**
     * 股票名字
     */
    private String gupiaoName;

    /**
     * 板块代码
     */
    private String plateCode;

    /**
     * 板块名字
     */
    private String plateName;
}