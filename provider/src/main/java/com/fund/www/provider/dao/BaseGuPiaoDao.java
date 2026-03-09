package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.BaseGuPiao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseGuPiaoDao {
    /**
     * 批量插入股票基础信息
     *
     * @param guPiaoList 股票对象列表
     * @return int
     */
    int batchInsertBaseGuPiao(@Param("guPiaoList") List<BaseGuPiao> guPiaoList);

    /**
     * 根据编码查询股票基础信息
     *
     * @param codeList 编码列表
     * @return List
     */
    List<BaseGuPiao> queryByCodeList(@Param("codeList") List<String> codeList);

    /**
     * 瀑布流查询股票基础信息
     *
     * @param startId 开始 ID
     * @return List
     */
    List<BaseGuPiao> queryByPageList(@Param("startId") Long startId);

    /**
     * 瀑布流查询股票基础信息
     *
     * @param startId 开始 ID
     * @param pageSize 每页大小
     * @return List
     */
    List<BaseGuPiao> queryByPageWithSize(@Param("startId") Long startId, @Param("pageSize") Integer pageSize);

    /**
     * 删除股票
     *
     * @param codeList 编码列表
     * @return int
     */
    int deleteByCodeList(@Param("codeList") List<String> codeList);

    /**
     * 获取最大 ID
     *
     * @return Long
     */
    Long getMaxId();
}
