package com.fund.www.provider.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class FundSubjectDTO implements Serializable {
    @JSONField(name = "stype")
    private String code;

    @JSONField(name = "sname")
    private String name;
}
