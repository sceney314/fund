package com.fund.www.provider.task.trigger.impl;

import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.common.WorkerStatusEnum;
import com.fund.www.provider.common.WorkerTypeEnum;
import com.fund.www.provider.common.constants.FundConst;
import com.fund.www.provider.common.constants.TxlConst;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.task.trigger.WorkerTriggerService;
import com.fund.www.provider.utils.GuavaCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 股票板块检查
 */
@Slf4j
@Component
public class TriggerCheckPlateService implements WorkerTriggerService {
    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Override
    public void trigger() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TxlConst.DATE_PATTERN_YYYY_MM_DD));
        String key = "FUTU_OWNER_PLATE_" + date;
        String val = GuavaCacheUtils.getSwitchCache(key);
        if (Objects.equals(FundConst.YES_STRING, val)){
            System.out.println("日期[" + date + "][股票板块检查 worker]，今日已执行，不再执行");
            return;
        }
        GuPiaoWorker worker = guPiaoWorkerDao.queryByWorkerType(WorkerTypeEnum.TYPE_OWNER_PLATE_CHECK.getCode(), LocalDate.now());
        if (Objects.nonNull(worker)){
            GuavaCacheUtils.putSwitchCache(key, FundConst.YES_STRING);
            System.out.println("日期[" + date + "][股票板块检查 worker]，已存在，执行结束");
            return;
        }

        worker = new GuPiaoWorker();
        worker.setSignalDate(LocalDate.now());
        worker.setWorkerType(WorkerTypeEnum.TYPE_OWNER_PLATE_CHECK.getCode());
        worker.setWorkerStatus(WorkerStatusEnum.INIT.getCode());
        worker.setRemark("0");
        if (guPiaoWorkerDao.insertWorker(worker) < 1){
            throw new ServiceException("添加 worker[" + WorkerTypeEnum.TYPE_OWNER_PLATE_CHECK.getMsg() + "] 异常");
        }

        GuavaCacheUtils.putSwitchCache(key, FundConst.YES_STRING);
        System.out.println("日期[" + date + "][股票板块检查 worker]，执行结束");
    }

}
