package com.fund.www.provider.task.worker;

import com.fund.www.provider.bean.po.GuPiaoWorker;

public interface WorkerService {
    /**
     * 处理 woerker
     *
     * @param worker worker
     */
    void processWorker(GuPiaoWorker worker);
}
