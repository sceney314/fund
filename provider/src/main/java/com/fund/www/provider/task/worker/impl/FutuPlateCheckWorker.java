package com.fund.www.provider.task.worker.impl;

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
import com.fund.www.provider.task.worker.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 股票板块
 *
 * @date 2025-05-20 7:59 上午
 */
@Slf4j
@Component
public class FutuPlateCheckWorker implements WorkerService {
    @Resource
    private FuTuService fuTuService;

    @Resource
    private FuTuFactory fuTuFactory;

    @Resource
    private BaseGuPiaoDao baseGuPiaoDao;

    @Resource
    private GuPiaoWorkerDao guPiaoWorkerDao;

    @Resource
    private PlateGuPiaoDao plateGuPiaoDao;

    @Override
    public Set<WorkerTypeEnum> workerType() {
        return Collections.singleton(WorkerTypeEnum.TYPE_OWNER_PLATE_CHECK);
    }

    @Override
    public void processWorker(GuPiaoWorker worker) {
        Long id  = baseGuPiaoDao.getMaxId();
        Integer pageSize = 50;
        Long startId = Objects.isNull(worker) || StringUtils.isBlank(worker.getRemark()) ? 0L: Long.parseLong(worker.getRemark());
        if (Objects.equals(startId, id)){
            return;
        }
        while (true){
            List<BaseGuPiao> piaoList = baseGuPiaoDao.queryByPageWithSize(startId, pageSize);
            if (CollectionUtils.isEmpty(piaoList)){
                break;
            }
            startId = piaoList.stream().map(BaseGuPiao::getId).max(Long::compareTo).orElseThrow(() -> new ServiceException("找最大 ID 异常"));
            checkGuPiaoPlate(piaoList, startId);
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                throw new ServiceException("股票板块检查等待异常");
            }
        }

        GuPiaoWorker plateWorker = guPiaoWorkerDao.queryByWorkerType(WorkerTypeEnum.TYPE_PLATE_CHECK.getCode(), LocalDate.now());
        if (Objects.isNull(plateWorker)){
            worker = new GuPiaoWorker();
            worker.setSignalDate(LocalDate.now());
            worker.setWorkerType(WorkerTypeEnum.TYPE_PLATE_CHECK.getCode());
            worker.setWorkerStatus(WorkerStatusEnum.FAIL.getCode());
            if (guPiaoWorkerDao.insertWorker(worker) < 1){
                throw new ServiceException("添加 " + WorkerTypeEnum.TYPE_PLATE_CHECK.getMsg() + "worker 异常");
            }
        }
    }

    /**
     * 检查股票板块信息
     *
     * @param piaoList 股票列表
     * @param maxId 最大 ID
     */
    private void checkGuPiaoPlate(List<BaseGuPiao> piaoList, Long maxId){
        List<StockPlateBO> boList = piaoList.stream()
                .map(piao -> new StockPlateBO(piao.getGupiaoCode(),  MarketEnum.getInstance(piao.getMarket())))
                .filter(p -> Objects.nonNull(p.getMarket()))
                .collect(Collectors.toList());
        List<PlateGuPiao> plateList = fuTuFactory.queryOwnerPlate(boList);
        if (CollectionUtils.isEmpty(plateList)){
            throw new ServiceException("获取股票板块异常");
        }
        Map<String, List<PlateGuPiao>> plateMap = plateList.stream().collect(Collectors.groupingBy(PlateGuPiao::getGupiaoCode));
        List<PlateGuPiao> existPlateList = plateGuPiaoDao.queryByGupiaoCodeList(piaoList.stream().map(BaseGuPiao::getGupiaoCode).collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(existPlateList)){
            fuTuService.asyncCheckOwnerPlate(plateList, null, maxId);
            return;
        }
        Map<String, List<PlateGuPiao>> existMap = existPlateList.stream().collect(Collectors.groupingBy(PlateGuPiao::getGupiaoCode));
        List<PlateGuPiao> addList = new ArrayList<>();
        List<String> deleteCodeList = new ArrayList<>();
        plateMap.forEach((code, list) -> {
            List<PlateGuPiao> existList = existMap.getOrDefault(code, null);
            if (CollectionUtils.isEmpty(existList)){
                addList.addAll(list);
            }else{
                List<String> plateCodeList = list.stream().map(PlateGuPiao::getPlateCode).distinct().collect(Collectors.toList());
                List<String> existCodeList = existList.stream().map(PlateGuPiao::getPlateCode).distinct().collect(Collectors.toList());
                if (!existCodeList.containsAll(plateCodeList) || !plateCodeList.containsAll(existCodeList)){
                    deleteCodeList.add(code);
                    addList.addAll(list);
                }
            }
        });
        fuTuService.asyncCheckOwnerPlate(addList, deleteCodeList, maxId);
    }
}
