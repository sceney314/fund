package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.GuPiaoWorker;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface GuPiaoWorkerDao {
    /**
     * 批量插入worker
     *
     * @param workerList worker 列表
     * @return int
     */
    int batchInsertWorker(@Param("workerList")List<GuPiaoWorker> workerList);

    /**
     * 查询初始化 worker
     *
     * @return List
     */
    List<GuPiaoWorker> queryInitWorker();

    /**
     * 查询初始化 worker
     *
     * @return List
     */
    List<GuPiaoWorker> queryFailWorker();

    /**
     * 根据信号日期查询初始化worker
     *
     * @param signalDate 信号日期
     * @return List
     */
    List<GuPiaoWorker> queryBySignalDate(@Param("signalDate") LocalDate signalDate);

    /**
     * 根据ID锁定worker
     *
     * @param id ID
     * @return int
     */
    int lockWorkerById(@Param("id") Long id);

    /**
     * 根据 ID 重置 worker
     *
     * @param id ID
     * @return int
     */
    int resetWorkerById(@Param("id") Long id);

    /**
     * 根据ID结束 worker 并更新成功
     *
     * @param id ID
     * @return int
     */
    int finishWorkerSuccess(@Param("id") Long id);

    /**
     * 根据ID结束 worker 并更新失败
     *
     * @param id ID
     * @return int
     */
    int finishWorkerFail(@Param("id") Long id);
}