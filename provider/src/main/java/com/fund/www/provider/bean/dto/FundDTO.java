package com.fund.www.provider.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 基金
 */
@Setter
@Getter
@ToString
public class FundDTO implements Serializable {
    /**
     * 基金编码
     */
    @JSONField(name = "symbol")
    private String code;

    /**
     * 基金名字
     */
    private String name;

    /**
     * 基金分类
     */
    @JSONField(name = "Type1Id")
    private String codeOne;

    /**
     * 基金类型
     */
    @JSONField(name = "Type2Id")
    private String typeTwo;

    /**
     * 基金子类型
     */
    @JSONField(name = "Type3Id")
    private String typeThree;

    /**
     * 基金公司名字
     */
    @JSONField(name = "CompanyName")
    private String companyName;

    /**
     * 主题名字
     */
    @JSONField(name = "SubjectName")
    private String subjectName;

    /**
     * 成立时间
     */
    @JSONField(name = "clrq")
    private String createTime;

    /**
     * 基金净值
     */
    @JSONField(name = "dwjz")
    private String netValue;

    /**
     * 累计净值
     */
    @JSONField(name = "ljjz")
    private String sumNet;

    /**
     * 基金规模
     */
    @JSONField(name = "jjgm")
    private String fundSize;

    /**
     * 最近一周 涨幅
     */
    @JSONField(name = "w1navg")
    private String oneWeek;

    /**
     * 最近一个月涨幅
     */
    @JSONField(name = "w4navg")
    private String oneMonth;

    /**
     * 最近三个月涨幅
     */
    @JSONField(name = "w13navg")
    private String threeMonth;

    /**
     * 最近六个月涨幅
     */
    @JSONField(name = "w26navg")
    private String sixMonth;

    /**
     * 最近一年涨幅
     */
    @JSONField(name = "w52navg")
    private String oneYear;

    /**
     * 最近三年涨幅
     */
    @JSONField(name = "y3navg")
    private String threeYear;

    /**
     * 最近五年涨幅
     */
    @JSONField(name = "y5navg")
    private String fiveYear;

    /**
     * 成立以来涨幅
     */
    @JSONField(name = "slnavg")
    private String allYear;

    /**
     * 今年以来涨幅
     */
    @JSONField(name = "ynyl")
    private String currentYear;
}
