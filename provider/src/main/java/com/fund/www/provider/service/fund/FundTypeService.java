package com.fund.www.provider.service.fund;

import com.fund.www.provider.bean.po.FundType;

import java.util.List;

public interface FundTypeService {
    /**
     * 初始化基金股票
     */
    void initFundType();

    /**
     * 获取全部类型
     *
     * @return List
     */
    List<FundType> getAllType();
}
