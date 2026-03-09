package com.fund.www.provider.task.impl;

import com.fund.www.provider.task.AsynTriggerTaskService;
import com.fund.www.provider.task.trigger.WorkerTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AsynTriggerTaskServiceImpl implements AsynTriggerTaskService {
    @Autowired
    private WorkerTriggerService[] workerTriggerServices;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @PostConstruct
    private void trigger(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, r -> new Thread(new ThreadGroup("FUND-TASK"), r, "FUND-TASK-TRIGGER"));
        executorService.scheduleAtFixedRate(() -> {
            for (WorkerTriggerService triggerService : workerTriggerServices){
                threadPoolTaskExecutor.submit(() -> {
                    try {
                        triggerService.trigger();
                    }catch (Exception e){
                        log.error("触发 worker 执行异常", e);
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                });
            }
        }, 10L, 10L, TimeUnit.SECONDS);
    }


}
