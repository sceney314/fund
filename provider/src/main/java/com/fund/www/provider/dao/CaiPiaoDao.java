package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.CaiPiao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaiPiaoDao {
    /**
     * 批量插入彩票
     *
     * @param caiPiaoList 彩票对象
     * @return int
     */
    int batchInsertCaiPiao(@Param("caiPiaoList") List<CaiPiao> caiPiaoList);

    /**
     * 获取彩票数据
     *
     * @param type 彩票类型
     * @param codeList 期号猎豹
     * @return List
     */
    List<CaiPiao> getByCaiPiaoCode(@Param("type") Integer type, @Param("codeList") List<String> codeList);
}