package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockDao {
    /**
     * 批量插入基金持有股票
     *
     * @param stockList 股票列表
     * @return int
     */
    int insertStockList(@Param("stockList") List<Stock> stockList);

    /**
     * 根据代码股票查询信息
     *
     * @param codeList 代码股票列表
     * @return List
     */
    List<Stock> queryByCodeList(@Param("codeList") List<String> codeList, @Param("fundCode") String fundCode);

    /**
     * 更新股票信息
     *
     * @param stock 基金股票
     * @return int
     */
    int updateFundStock(@Param("stock") Stock stock);

    /**
     * 瀑布流获取基金重仓股票
     *
     * @param startId 开始 ID
     * @param pageSize 每页数量
     * @return List
     */
    List<Stock> queryStockFlow(@Param("startId") Long startId, @Param("pageSize") Integer pageSize);
}