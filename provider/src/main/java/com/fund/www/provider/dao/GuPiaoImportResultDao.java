package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.GuPiaoImportResult;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface GuPiaoImportResultDao {
    /**
     * 插入导入结果
     *
     * @param result 导入结果
     * @return int
     */
    int insertImportResult(@Param("result") GuPiaoImportResult result);

    /**
     * 根据信号和导入类型查询导入结果
     *
     * @param signalDate 信号日期
     * @param ployType 导入类型
     * @return GuPiaoAnalyzeResult
     */
    GuPiaoImportResult queryBySignalAndType(@Param("signalDate") LocalDate signalDate, @Param("ployType") Integer ployType);

    /**
     * 根据信号和导入类型查询导入结果
     *
     * @param signal 信号日期
     * @return List
     */
    List<GuPiaoImportResult> queryBySignalDate(@Param("signalDate") LocalDate signal);

    /**
     * 查询最后一个导入完成的
     *
     * @return GuPiaoImportResult
     */
    GuPiaoImportResult queryLastImportSignal();

    /**
     * 将状态更新成已分析
     *
     * @param signalDate 信号日期
     * @return int
     */
    int updateAnalyzeStatusBySignalDate(@Param("signalDate") LocalDate signalDate);

}