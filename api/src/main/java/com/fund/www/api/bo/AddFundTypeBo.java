package com.fund.www.api.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class AddFundTypeBo implements Serializable {
    // 基金编码
    private String code;

    // 基金名字
    private String name;
}
