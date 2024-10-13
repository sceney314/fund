package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.FundType;
import org.apache.ibatis.annotations.Param;

public interface FundTypeDao {
    /**
     * 添加基金类型
     *
     * @param type 基金类型对象
     * @return int
     */
    int addFundType(FundType type);


    FundType getById(@Param("id") Integer id);
}