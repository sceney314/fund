package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.GuPiaoAnalyzeResult;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface GuPiaoAnalyzeResultDao {
    /**
     * 批量插入分析结果
     *
     * @param resultList 结果列表
     * @return int
     */
    int batchInsertReuslt(@Param("resultList")List<GuPiaoAnalyzeResult> resultList);

    /**
     * 根据信号日期查询
     *
     * @param signalDate 信号日期
     * @return List
     */
    List<GuPiaoAnalyzeResult> queryBySignalDate(@Param("signalDate")LocalDate signalDate);
}