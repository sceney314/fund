package com.fund.www.provider.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class FundCompanyDTO implements Serializable {
    /**
     * 基金编码
     */
    @JSONField(name = "cid")
    private String code;

    /**
     * 基金公司名字
     */
    @JSONField(name = "cname")
    private String name;

    /**
     * 基金公司名字
     */
    @JSONField(name = "cpy")
    private String shortName;
}
