package com.fund.www.provider.bean.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class FundTypeDTO implements Serializable {
    /**
     * 名字
     */
    private String name;

    /**
     * 类型 code
     */
    private String typeCode;

    /**
     * 父结构编码
     */
    private String parentCode;

    public FundTypeDTO() {
    }

    public FundTypeDTO(String name, String typeCode) {
        this.name = name;
        this.typeCode = typeCode;
    }
}
