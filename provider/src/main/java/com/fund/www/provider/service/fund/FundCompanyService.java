package com.fund.www.provider.service.fund;

import com.fund.www.provider.bean.po.FundCompany;

import java.util.List;

public interface FundCompanyService {
    /**
     * 初始化基金股票
     */
    void initFundCompany();

    /**
     * 获取全部基金公司
     *
     * @return List
     */
    List<FundCompany> getAllCompany();
}
