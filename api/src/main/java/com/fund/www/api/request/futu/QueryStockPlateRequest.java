package com.fund.www.api.request.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class QueryStockPlateRequest implements Serializable {
    /**
     * 股票编码
     */
    private String codeList;

    public void validate(){
        if (StringUtils.isBlank(codeList)){
            throw new IllegalArgumentException("股票代码不能空");
        }
    }

}
