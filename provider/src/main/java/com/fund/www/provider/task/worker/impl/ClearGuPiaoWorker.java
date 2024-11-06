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
 * 清理股票数据，将 15 个交易日以前的数据设置成不可用
 */
@Slf4j
@Component
public class ClearGuPiaoWorker implements WorkerService {
    /**
     * 需要清理交易日期数
     */
    private static final Integer PRE_SIGNAL_DATE = 15;

    @Resource
    private GuPiaoImportResultDao guPiaoImportResultDao;

    @Resource
    private GuPiaoDao guPiaoDao;

    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Override
    public Set<WorkerTypeEnum> workerType() {
        return Collections.singleton(WorkerTypeEnum.TYPE_GU_PIAO_CLEAR);
    }

    @Override
    public void processWorker(GuPiaoWorker worker) {
        // 执行 worker
        doClearData(worker);

        // 更新 worker
        guPiaoWorkerDao.finishWorkerSuccess(worker.getId());
    }

    /**
     * 清理数据
     *
     * @param worker worker 对象
     */
    private void doClearData(GuPiaoWorker worker){
        List<LocalDate> signalDateList = guPiaoImportResultDao.querySignalDateList(PRE_SIGNAL_DATE);
        if (CollectionUtils.isEmpty(signalDateList) || signalDateList.size() < PRE_SIGNAL_DATE){
            return;
        }

        LocalDate minDate = signalDateList.stream().min(LocalDate::compareTo).orElse(null);
        if (Objects.isNull(minDate)){
            return;
        }

        guPiaoDao.clearByBySignalDate(minDate);
    }
}
