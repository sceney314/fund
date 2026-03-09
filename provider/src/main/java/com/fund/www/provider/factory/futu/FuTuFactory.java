package com.fund.www.provider.factory.futu;

import com.fund.www.provider.bean.bo.StockPlateBO;
import com.fund.www.provider.bean.po.PlateGuPiao;

import java.util.List;

public interface FuTuFactory {
    /**
     * 获取股票 板块
     *
     * @param boList 股票列表
     */
    void getAsyncOwnerPlate(List<StockPlateBO> boList);

    /**
     * 根据股票编码查询板块
     *
     * @param boList 股票列表
     * @return List
     */
    List<PlateGuPiao> queryOwnerPlate(List<StockPlateBO> boList);

}
