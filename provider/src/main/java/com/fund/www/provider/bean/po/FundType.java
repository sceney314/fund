package com.fund.www.provider.bean.po;

import com.fund.www.provider.utils.IdgentTestUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class FundType {
    private Integer id;

    private String fundId;

    private String sinaTypeCode;

    private String typeName;

    private Integer isUse;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static FundType makeType(String code, String name){
        FundType type = new FundType();
        type.setFundId(IdgentTestUtil.nextId() + "");
        type.setSinaTypeCode(code);
        type.setTypeName(name);
        type.setCreateTime(LocalDateTime.now());
        return type;
    }
}