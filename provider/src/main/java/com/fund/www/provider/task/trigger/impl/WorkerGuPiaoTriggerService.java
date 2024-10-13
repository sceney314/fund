package com.fund.www.provider.task.trigger.impl;


import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.task.WorkerTriggerService;
import com.fund.www.provider.task.worker.impl.AnalyzeGuPiaoWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class WorkerGuPiaoTriggerService implements WorkerTriggerService {
    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private AnalyzeGuPiaoWorker analyzeGuPiaoWorker;

    @Override
    public void trigger() {
        List<GuPiaoWorker> failWorkerList = guPiaoWorkerDao.queryFailWorker();
        if (CollectionUtils.isNotEmpty(failWorkerList)){
            threadPoolTaskExecutor.submit(() -> failWorkerList.forEach(worker -> guPiaoWorkerDao.resetWorkerById(worker.getId())));
        }

        List<GuPiaoWorker> workerList = guPiaoWorkerDao.queryInitWorker();
        if (CollectionUtils.isEmpty(workerList)){
            return;
        }
        workerList.forEach(worker -> {
            try {
                analyzeGuPiaoWorker.processWorker(worker);
            }catch (Exception e){
                log.error("worker[" + worker.toString()+ "] 执行异常", e);
            }
        });
    }
}
