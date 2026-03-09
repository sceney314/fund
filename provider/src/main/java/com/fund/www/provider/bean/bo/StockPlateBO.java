package com.fund.www.provider.bean.bo;

import com.fund.www.provider.common.futu.MarketEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class StockPlateBO implements Serializable {
    /**
     * 股票代码
     */
    private String gupiaoCode;

    /**
     * 市场
     */
    private MarketEnum market;

    public StockPlateBO() {
    }

    public StockPlateBO(String gupiaoCode, MarketEnum market) {
        this.gupiaoCode = gupiaoCode;
        this.market = market;
    }
}
