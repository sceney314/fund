package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.PlateInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlateInfoDao {
    /**
     * 批量插入板块
     *
     * @param plateList 板块列表
     * @return int
     */
    int batchInsertPlate(@Param("plateList")List<PlateInfo> plateList);

    /**
     * 查询全量板块
     *
     * @return List
     */
    List<PlateInfo> queryAllPlate();
}