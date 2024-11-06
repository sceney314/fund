package com.fund.www.provider.task.worker;

import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.common.WorkerTypeEnum;

import java.util.Set;

public interface WorkerService {
    /**
     * worker 类型
     *
     * @return WorkerTypeEnum
     */
    Set<WorkerTypeEnum> workerType();

    /**
     * 处理 woerker
     *
     * @param worker worker
     */
    void processWorker(GuPiaoWorker worker);
}
