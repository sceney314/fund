package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.GuPiaoAnalyzeResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuPiaoAnalyzeResultDao {
    /**
     * 批量插入分析结果
     *
     * @param resultList 结果列表
     * @return int
     */
    int batchInsertReuslt(@Param("resultList")List<GuPiaoAnalyzeResult> resultList);
}