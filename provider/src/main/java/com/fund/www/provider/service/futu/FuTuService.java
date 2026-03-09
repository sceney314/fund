package com.fund.www.provider.service.futu;

import com.fund.www.provider.bean.po.PlateGuPiao;

import java.util.List;

public interface FuTuService {
    /**
     * 根据股票编码查询板块
     *
     * @param stockCodeList 股票列表
     * @return List
     */
    List<PlateGuPiao> queryOwnerPlate(List<String> stockCodeList);

    /**
     * 异步校验股票板块
     *
     * @param plateList 板块列表
     * @param deleteCodeList 股票代码列表
     * @param maxId 最大 ID
     */
    void asyncCheckOwnerPlate(List<PlateGuPiao> plateList, List<String> deleteCodeList, Long maxId);
}
