package com.fund.www.provider.task.trigger.impl;

import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.common.WorkerTypeEnum;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.task.trigger.WorkerTriggerService;
import com.fund.www.provider.task.worker.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class WorkerGuPiaoTriggerService implements WorkerTriggerService {
    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    private Map<WorkerTypeEnum, WorkerService> serviceMap;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private void init(WorkerService[] services){
        Map<WorkerTypeEnum, WorkerService> map = new HashMap<>();
        for (WorkerService service : services){
            service.workerType().forEach(type -> map.put(type, service));
        }
        serviceMap = map;
    }

    /**
     * 获取实例
     *
     * @param worker worker
     * @return WorkerService
     */
    private WorkerService getInstance(GuPiaoWorker worker){
        WorkerTypeEnum type = WorkerTypeEnum.getInstance(worker.getWorkerType());
        if (Objects.isNull(type) || !serviceMap.containsKey(type)){
            return null;
        }
        return serviceMap.get(type);
    }

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
                // 锁定 worker
                if (guPiaoWorkerDao.lockWorkerById(worker.getId()) < 1){
                    throw new ServiceException("锁定 worker 失败，Worker:" + worker.getId());
                }
                System.out.println("worker 类型:[" + worker.getWorkerType() + "], worker:[" + worker.getId() + " - " + worker.getSignalDate() + "]锁定成功");
                WorkerService service = getInstance(worker);
                if (Objects.isNull(service)){
                    throw new ServiceException("worker 类型[" + worker.getWorkerType() + "]不存在对应的执行逻辑，worker:" + worker.getId());
                }
                service.processWorker(worker);
                System.out.println("worker 类型:[" + worker.getWorkerType() + "], worker:[" + worker.getId() + " - " + worker.getSignalDate() + "]执行结束");
            }catch (Exception e){
                guPiaoWorkerDao.finishWorkerFail(worker.getId());
                System.out.println("worker 类型:[" + worker.getWorkerType() + "], worker:[" + worker.getId() + " - " + worker.getSignalDate() + "]执行失败");
                log.error("worker[" + worker + "] 执行异常", e);
            }
        });
    }
}
