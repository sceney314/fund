package com.fund.www.provider.dao;

import com.fund.www.provider.bean.bo.QueryBySignalDateBO;
import com.fund.www.provider.bean.po.GuPiao;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface GuPiaoDao {
    /**
     * 批量插入信号
     *
     * @param piaoList 股票列表
     * @return int
     */
    int batchInsertSignal(@Param("piaoList") List<GuPiao> piaoList);

    /**
     * 根据信号日期查询
     *
     * @param bo 信号日期
     * @return List
     */
    List<GuPiao> queryBySignalDate(QueryBySignalDateBO bo);

    /**
     * 根据信号范围日期查询
     *
     * @param start 信号开始日期
     * @param end 信号结束日期
     * @return List
     */
    List<GuPiao> queryByRangeSignalDate(@Param("startSignal") LocalDate start, @Param("endSignal") LocalDate end);

    /**
     * 根据信号范围日期查询
     *
     * @param createTIme 插入时间
     * @param start 信号开始日期
     * @param end 信号结束日期
     * @return List
     */
    List<GuPiao> queryByRangeSignalDate(@Param("createTIme") LocalDateTime createTIme,
                                        @Param("startSignal") LocalDate start,
                                        @Param("endSignal") LocalDate end);
}