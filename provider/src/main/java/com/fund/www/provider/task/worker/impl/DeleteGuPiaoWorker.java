package com.fund.www.provider.task.worker.impl;

import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.common.WorkerTypeEnum;
import com.fund.www.provider.dao.GuPiaoDao;
import com.fund.www.provider.dao.GuPiaoImportResultDao;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.task.worker.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 删除股票数据，将 30 个交易日之前的数据删除
 */
@Slf4j
@Component
public class DeleteGuPiaoWorker implements WorkerService {
    /**
     * 需要清理交易日期数
     */
    private static final Integer DELETE_SIGNAL_DATE = 30;

    @Resource
    private GuPiaoImportResultDao guPiaoImportResultDao;

    @Resource
    private GuPiaoDao guPiaoDao;

    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Override
    public Set<WorkerTypeEnum> workerType() {
        return Collections.singleton(WorkerTypeEnum.TYPE_GU_PIAO_DELETE);
    }

    @Override
    public void processWorker(GuPiaoWorker worker) {
        // 执行 worker
        doDeleteData(worker);

        // 更新 worker
        guPiaoWorkerDao.finishWorkerSuccess(worker.getId());
    }

    /**
     * worker
     *
     * @param worker 删除数据 worker
     */
    private void doDeleteData(GuPiaoWorker worker){
        List<LocalDate> signalDateList = guPiaoImportResultDao.querySignalDateList(DELETE_SIGNAL_DATE);
        if (CollectionUtils.isEmpty(signalDateList) || signalDateList.size() < DELETE_SIGNAL_DATE){
            return;
        }

        LocalDate minDate = signalDateList.stream().min(LocalDate::compareTo).orElse(null);
        if (Objects.isNull(minDate)){
            return;
        }

        guPiaoDao.deleteBySignalDate(minDate);
    }
}
