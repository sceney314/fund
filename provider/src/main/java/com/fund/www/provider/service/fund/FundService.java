package com.fund.www.provider.service.fund;

import com.fund.www.provider.bean.dto.StockDTO;

import java.util.List;

public interface FundService {
    /**
     * 初始化基金
     */
    void initFund();

    /**
     * 初始化基金
     */
    void getFundInfo();

    /**
     * 获取分析结果
     *
     * @return String
     */
    String analyzeStockByFund();
}
