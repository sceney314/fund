package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 证券标识
 */
@Setter
@Getter
@ToString
public class SecurityDTO implements Serializable {
    /**
     * QotMarket，行情市场
     */
    private int market = 1;

    /**
     * 代码
     */
    private int code = 2;
}
