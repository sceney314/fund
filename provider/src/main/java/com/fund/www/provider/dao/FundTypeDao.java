package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.FundType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FundTypeDao {
    /**
     * 添加基金类型
     *
     * @param type 基金类型对象
     * @return int
     */
    int addFundType(FundType type);

    /**
     * 批量插入基金类型
     *
     * @param typeList 对象列表
     * @return int
     */
    int batchInsertFundType(@Param("typeList") List<FundType> typeList);


    FundType getById(@Param("id") Integer id);

    /**
     * 清除数据
     *
     * @return int
     */
    int clearFundTypeData();
}