package com.fund.www.provider.bean.dto.sina;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 股票基础信息
 */
@Setter
@Getter
@ToString
public class BasicGuPiaoDTO implements Serializable {
    private String code;
    private String name;
    private String symbol;
}
