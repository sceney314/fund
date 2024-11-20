package com.fund.www.provider.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@ToString
public class FundTypeItemDTO implements Serializable {
    /**
     * 名字
     */
    private String name;

    @JSONField(name = "types")
    private Map<String, String> typeMap;
}
