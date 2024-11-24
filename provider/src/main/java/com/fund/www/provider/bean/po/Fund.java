package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Fund extends Base{
    /**
     * 基金编码
     */
    private String fundCode;

    /**
     * 基金名字
     */
    private String fundName;

    /**
     * 1 级分类
     */
    private String typeCodeOne;

    /**
     * 2 级分类
     */
    private String typeCodeTwo;

    /**
     * 3 级分类
     */
    private String typeCodeThree;

    /**
     * 基金主题
     */
    private String subjectCode;

    /**
     * 基金名字
     */
    private String subjectName;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 公司名字
     */
    private String companyName;

    /**
     * 基金规模
     */
    private String fundSize;

    /**
     * 基金净值
     */
    private String netValue;

    /**
     * 累计净值
     */
    private String totalNetValue;

    /**
     * 最近 1 月涨幅
     */
    private String oneMonth;

    /**
     * 最近 3 月涨幅
     */
    private String threeMonth;

    /**
     * 最近 6 月涨幅
     */
    private String sixMonth;

    /**
     * 最近 1 年涨幅
     */
    private String oneYear;

    /**
     * 最近 3 年涨幅
     */
    private String threeYear;

    /**
     * 最近 5 年涨幅
     */
    private String fiveYear;

    /**
     * 成立至今
     */
    private String toThisDay;
}