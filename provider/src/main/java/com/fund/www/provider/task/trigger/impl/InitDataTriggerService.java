package com.fund.www.provider.task.trigger.impl;

import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.common.WorkerStatusEnum;
import com.fund.www.provider.common.WorkerTypeEnum;
import com.fund.www.provider.common.constants.FundConst;
import com.fund.www.provider.common.constants.TxlConst;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.task.trigger.WorkerTriggerService;
import com.fund.www.provider.utils.GuavaCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Slf4j
public class InitDataTriggerService implements WorkerTriggerService {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Override
    public void trigger() {
        initClearGuPiaoWorker();
        initDeleteGuPiaoWorker();
    }

    /**
     * 初始化删除股票 worker
     */
    private void initDeleteGuPiaoWorker(){
        threadPoolTaskExecutor.submit(() -> {
            LocalDate now = LocalDate.now();
            String key = "GU_PIAO_DELETE" + now.format(DateTimeFormatter.ofPattern(TxlConst.DATE_PATTERN_YYYY_MM_DD));
            initWorker(key, now, WorkerTypeEnum.TYPE_GU_PIAO_DELETE);
        });
    }

    /**
     * 初始化清理股票 worker
     */
    private void initClearGuPiaoWorker(){
        threadPoolTaskExecutor.submit(() -> {
            LocalDate now = LocalDate.now();
            String key = "GU_PIAO_CLEAR" + now.format(DateTimeFormatter.ofPattern(TxlConst.DATE_PATTERN_YYYY_MM_DD));
            initWorker(key, now, WorkerTypeEnum.TYPE_GU_PIAO_CLEAR);
        });
    }

    /**
     * 初始化 worker
     *
     * @param key worker key
     * @param signalDate 信号日期
     * @param workerType worker 类型
     */
    private void initWorker(String key, LocalDate signalDate, WorkerTypeEnum workerType){
        String val = GuavaCacheUtils.getSwitchCache(key);
        if (Objects.equals(FundConst.YES_STRING, val)){
            System.out.println("日期[" + signalDate + "],初始化类型:[" + key + "]:今日已执行, 本次不执行");
            return;
        }
        GuPiaoWorker worker = guPiaoWorkerDao.queryByWorkerType(workerType.getCode(), signalDate);
        if (Objects.nonNull(worker)){
            System.out.println("日期[" + signalDate + "],初始化类型:[" + key + "]:今日已执行, 本次不执行");
            GuavaCacheUtils.putSwitchCache(key, FundConst.YES_STRING);
            return;
        }
        worker = new GuPiaoWorker();
        worker.setWorkerType(workerType.getCode());
        worker.setSignalDate(signalDate);
        worker.setWorkerStatus(WorkerStatusEnum.INIT.getCode());
        if (guPiaoWorkerDao.insertWorker(worker) > 0){
            System.out.println("日期[" + signalDate + "],初始化类型:[" + key + "]:今日添加 worker 成功");
            GuavaCacheUtils.putSwitchCache(key, FundConst.YES_STRING);
        }else{
            System.out.println("初始化 worker[" + workerType + "], signal date[" + signalDate + "]异常");
        }
    }
}
