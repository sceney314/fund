package com.fund.www.provider.service.futu.impl;

import com.fund.www.provider.bean.bo.StockPlateBO;
import com.fund.www.provider.bean.po.BaseGuPiao;
import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.bean.po.PlateGuPiao;
import com.fund.www.provider.common.WorkerStatusEnum;
import com.fund.www.provider.common.WorkerTypeEnum;
import com.fund.www.provider.common.futu.MarketEnum;
import com.fund.www.provider.dao.BaseGuPiaoDao;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.dao.PlateGuPiaoDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.factory.futu.FuTuFactory;
import com.fund.www.provider.service.futu.FuTuService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 富途
 */
@Slf4j
@Service
public class FuTuServiceImpl implements FuTuService {
    @Resource
    private FuTuFactory fuTuFactory;

    @Resource
    private BaseGuPiaoDao baseGuPiaoDao;

    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Resource
    private PlateGuPiaoDao plateGuPiaoDao;

    @Override
    public List<PlateGuPiao> queryOwnerPlate(List<String> stockCodeList) {
        List<BaseGuPiao> piaoList = baseGuPiaoDao.queryByCodeList(stockCodeList);
        if (CollectionUtils.isEmpty(piaoList)){
            return new ArrayList<>();
        }
        List<StockPlateBO> boList = piaoList.stream()
                .map(piao -> new StockPlateBO(piao.getGupiaoCode(),  MarketEnum.getInstance(piao.getMarket())))
                .filter(p -> Objects.nonNull(p.getMarket()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(boList)){
            return new ArrayList<>();
        }

        List<List<StockPlateBO>> groupList = Lists.partition(boList, 200);
        List<PlateGuPiao> result = new ArrayList<>();
        groupList.forEach(item -> {
            result.addAll(fuTuFactory.queryOwnerPlate(item));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void asyncCheckOwnerPlate(List<PlateGuPiao> plateList, List<String> deleteCodeList, Long maxId) {
        GuPiaoWorker worker = guPiaoWorkerDao.queryByWorkerType(WorkerTypeEnum.TYPE_OWNER_PLATE_CHECK.getCode(), LocalDate.now());
        if (Objects.nonNull(worker)){
            guPiaoWorkerDao.updateWorkerRemark(worker.getId(), maxId.toString());
        }
        if (CollectionUtils.isNotEmpty(deleteCodeList) && plateGuPiaoDao.deleteByGupiaoCode(deleteCodeList) < 1){
            throw new ServiceException("根据股票代码删除板块异常");
        }

        if (CollectionUtils.isNotEmpty(plateList) && plateGuPiaoDao.batchInsertPlate(plateList) < 1){
            throw new ServiceException("根据股票代码删除板块异常");
        }
    }
}
