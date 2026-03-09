package com.fund.www.provider.service.gupiao.impl;

import com.fund.www.provider.bean.model.ImportModel;
import com.fund.www.provider.bean.po.GuPiaoAnalyzeResult;
import com.fund.www.provider.bean.po.GuPiaoImportResult;
import com.fund.www.provider.common.PloyTypeEnum;
import com.fund.www.provider.dao.GuPiaoAnalyzeResultDao;
import com.fund.www.provider.dao.GuPiaoDao;
import com.fund.www.provider.dao.GuPiaoImportResultDao;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.service.gupiao.GuPiaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GuPiaoServiceImpl implements GuPiaoService {
    @Resource
    private GuPiaoDao guPiaoDao;

    @Resource
    private GuPiaoImportResultDao guPiaoImportResultDao;

    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Resource
    private GuPiaoAnalyzeResultDao guPiaoAnalyzeResultDao;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void triggerSignal(ImportModel model) {
        if (Objects.isNull(model) || CollectionUtils.isEmpty(model.getPiaoList())){
            return;
        }

        GuPiaoImportResult importResult = guPiaoImportResultDao.queryBySignalAndType(model.getSignalDate(), model.getPloyType());
        if(Objects.nonNull(importResult)){
            return;
        }

        if (guPiaoDao.batchInsertSignal(model.getPiaoList()) < 1){
            throw new ServiceException("插入导入明细失败");
        }

        if (guPiaoImportResultDao.insertImportResult(model.buildImportResult()) < 1){
            throw new ServiceException("插入导入结果失败");
        }

        List<GuPiaoImportResult> importList = guPiaoImportResultDao.queryBySignalDate(model.getSignalDate());
        if (CollectionUtils.isEmpty(importList)){
            return;
        }

        List<PloyTypeEnum> typeList = importList.stream().map(i -> PloyTypeEnum.getInstance(i.getPloyType())).distinct().collect(Collectors.toList());
        if (model.isForceAnalyze() || typeList.containsAll(PloyTypeEnum.getAllInstance())){
            if (guPiaoWorkerDao.batchInsertWorker(model.buildAnalyzeWorkerList()) < 1){
                throw new ServiceException("插入分析 worker 失败");
            }
        }
    }

    @Override
    public List<GuPiaoAnalyzeResult> showAnalyze() {
        GuPiaoImportResult result = guPiaoImportResultDao.queryLastImportSignal();
        if (Objects.isNull(result)){
            throw new ServiceException("不存在导入完成任务");
        }
        return guPiaoAnalyzeResultDao.queryBySignalDate(result.getSignalDate());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void forceAnalyze(LocalDate signalDate) {
        List<GuPiaoImportResult> importList = guPiaoImportResultDao.queryBySignalDate(signalDate);
        if (CollectionUtils.isEmpty(importList)){
            return;
        }

        ImportModel model = new ImportModel();
        model.setSignalDate(signalDate);
        if (guPiaoWorkerDao.batchInsertWorker(model.buildAnalyzeWorkerList()) < 1){
            throw new ServiceException("插入分析 worker 失败");
        }
    }
}
