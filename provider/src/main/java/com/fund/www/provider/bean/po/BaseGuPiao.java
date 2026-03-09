package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BaseGuPiao extends Base{
    /**
     * 股票代码
     */
    private String gupiaoCode;

    /**
     * 股票名字
     */
    private String gupiaoName;

    /**
     * 股票符号
     */
    private String symbol;

    /**
     * 股票市场
     */
    private Integer market;
}
