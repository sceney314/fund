package com.fund.www.provider.service.fund;

import com.fund.www.provider.bean.po.Stock;

import java.util.List;

public interface FundStockService {
    /**
     * 查询全部股票重进
     *
     * @return List
     */
    List<Stock> queryAllStock();
}
