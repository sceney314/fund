package com.fund.www.provider.task.worker.impl;

import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.bean.po.PlateGuPiao;
import com.fund.www.provider.bean.po.PlateInfo;
import com.fund.www.provider.common.WorkerTypeEnum;
import com.fund.www.provider.dao.PlateGuPiaoDao;
import com.fund.www.provider.dao.PlateInfoDao;
import com.fund.www.provider.task.worker.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 富途板块
 *
 * @date 2025-05-20 8:28 上午
 */
@Slf4j
@Component
public class FutuPlateWorker implements WorkerService {
    @Resource
    private PlateInfoDao plateInfoDao;

    @Resource
    private PlateGuPiaoDao plateGuPiaoDao;

    @Override
    public Set<WorkerTypeEnum> workerType() {
        return Collections.singleton(WorkerTypeEnum.TYPE_PLATE_CHECK);
    }

    @Override
    public void processWorker(GuPiaoWorker worker) {
        List<PlateGuPiao> plateList = plateGuPiaoDao.queryDistinctPlate();
        if (CollectionUtils.isEmpty(plateList)){
            return;
        }
        List<PlateInfo> infoList = plateInfoDao.queryAllPlate();
        if (CollectionUtils.isEmpty(infoList)){
            List<PlateInfo> list = plateList.stream()
                    .map(plate -> new PlateInfo(plate.getPlateCode(), plate.getPlateName()))
                    .filter(PlateInfo.distinctByKey(PlateInfo::getPlateCode))
                    .collect(Collectors.toList());
            plateInfoDao.batchInsertPlate(list);
            return;
        }
        Set<String> codeSet = infoList.stream().map(PlateInfo::getPlateCode).collect(Collectors.toSet());
        List<PlateInfo> list = plateList.stream()
                .filter(plate -> !codeSet.contains(plate.getPlateCode()))
                .map(plate -> new PlateInfo(plate.getPlateCode(), plate.getPlateName()))
                .filter(PlateInfo.distinctByKey(PlateInfo::getPlateCode))
                .collect(Collectors.toList());
        plateInfoDao.batchInsertPlate(list);
    }
}
