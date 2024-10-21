package com.fund.www.provider.task.worker.impl;

import com.fund.www.provider.bean.po.GuPiao;
import com.fund.www.provider.bean.po.GuPiaoAnalyzeResult;
import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.common.WorkerStatusEnum;
import com.fund.www.provider.common.WorkerTypeEnum;
import com.fund.www.provider.dao.GuPiaoAnalyzeResultDao;
import com.fund.www.provider.dao.GuPiaoDao;
import com.fund.www.provider.dao.GuPiaoImportResultDao;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.task.worker.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AnalyzeGuPiaoWorker implements WorkerService {
    @Resource
    private GuPiaoDao guPiaoDao;

    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Resource
    private GuPiaoImportResultDao guPiaoImportResultDao;

    @Resource
    private GuPiaoAnalyzeResultDao guPiaoAnalyzeResultDao;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void processWorker(GuPiaoWorker worker) {
        try {
            // 锁定 worker
            if (guPiaoWorkerDao.lockWorkerById(worker.getId()) < 1){
                throw new ServiceException("锁定 worker 失败，Worker:" + worker.getId());
            }

            // 分析
            analyzeGuPiao(worker);

            // 更新 worker
            guPiaoWorkerDao.finishWorkerSuccess(worker.getId());

            List<GuPiaoWorker> workerList = guPiaoWorkerDao.queryBySignalDate(worker.getSignalDate());
            if (CollectionUtils.isNotEmpty(workerList) && workerList.stream().allMatch(w -> Objects.equals(WorkerStatusEnum.SUCCESS.getCode(), w.getWorkerStatus()))){
                guPiaoImportResultDao.updateAnalyzeStatusBySignalDate(worker.getSignalDate());
            }
        }catch (Exception e){
            guPiaoWorkerDao.finishWorkerFail(worker.getId());
            log.error("worker 执行异常[" + worker + "]", e);
        }
    }

    /**
     * 股票分析
     *
     * @param worker worker
     */
    private void analyzeGuPiao(GuPiaoWorker worker){
        WorkerTypeEnum workerType = WorkerTypeEnum.getAnalyzeInstance(worker.getWorkerType());
        if (Objects.isNull(workerType)){
            return;
        }
        LocalDate startDate = worker.getSignalDate().minusDays(workerType.getCode()).plusDays(1);
        LocalDate endDate = worker.getSignalDate();

        List<GuPiao> piaoList = guPiaoDao.queryByRangeSignalDate(LocalDateTime.of(endDate, LocalTime.MAX), startDate, endDate);
        if (CollectionUtils.isEmpty(piaoList)){
            return;
        }

        Map<String, List<GuPiao>> piaoMap = piaoList.stream().collect(Collectors.groupingBy(GuPiao::getPiaoCode));
        List<GuPiaoAnalyzeResult> resultList = piaoMap.values().stream()
                .filter(item -> Objects.equals(WorkerTypeEnum.TYPE_ONE, workerType) || item.size() > 1)
                .flatMap(Collection::stream)
                .map(piao -> {
                    GuPiaoAnalyzeResult result = new GuPiaoAnalyzeResult();
                    result.setAnalyzeType(workerType.getCode());
                    result.setSignalDate(worker.getSignalDate());
                    result.setPiaoCode(piao.getPiaoCode());
                    result.setImportDate(piao.getSignalDate());
                    result.setPiaoName(piao.getPiaoName());
                    result.setCommissionPrice(piao.getCommissionPrice());
                    result.setTargetPrice(piao.getTargetPrice());
                    result.setPloyName(piao.getPloyName());
                    result.setTendency(piao.getTendency());
                    return result;
                })
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resultList)){
            return;
        }
        guPiaoAnalyzeResultDao.batchInsertReuslt(resultList);
    }
}
