package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.PlateGuPiao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlateGuPiaoDao {
    /**
     * 批量插入股票板块信息
     *
     * @param plateList 股票板块信息列表
     * @return int
     */
    int batchInsertPlate(@Param("plateList") List<PlateGuPiao> plateList);

    /**
     * 根据股票代码查询板块信息
     *
     * @param codeList 股票代码列表
     * @return List
     */
    List<PlateGuPiao> queryByGupiaoCodeList(@Param("codeList") List<String> codeList);

    /**
     * 根据股票 code 信息删除
     *
     * @param codeList 股票代码列表
     * @return int
     */
    int deleteByGupiaoCode(@Param("codeList") List<String> codeList);

    /**
     * 去重获取板块
     *
     * @return List
     */
    List<PlateGuPiao> queryDistinctPlate();
}