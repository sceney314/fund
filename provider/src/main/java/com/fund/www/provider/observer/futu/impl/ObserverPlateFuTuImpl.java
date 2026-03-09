package com.fund.www.provider.observer.futu.impl;

import com.fund.www.provider.bean.po.PlateGuPiao;
import com.fund.www.provider.bean.vo.PlateNode;
import com.fund.www.provider.common.constants.FuTuBlockQueue;
import com.fund.www.provider.common.futu.FuTuSubjectEnum;
import com.futu.openapi.pb.QotGetOwnerPlate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObserverPlateFuTuImpl extends AbstractObserverFuTuImpl{
    @Override
    public FuTuSubjectEnum subject() {
        return FuTuSubjectEnum.PLATE;
    }

    @Override
    protected <T> void processData(T data, int serialNo) {
        if (data instanceof QotGetOwnerPlate.Response){
            PlateNode node = new PlateNode(serialNo);
            QotGetOwnerPlate.Response response = (QotGetOwnerPlate.Response)data;
            List<PlateGuPiao> plateList = response.getS2C().getOwnerPlateListList()
                    .stream()
                    .map(item -> item.getPlateInfoListList()
                            .stream()
                            .map(plateInfo -> {
                                PlateGuPiao plate = new PlateGuPiao();
                                plate.setGupiaoName(item.getName());
                                plate.setGupiaoCode(item.getSecurity().getCode());
                                plate.setPlateName(plateInfo.getName());
                                plate.setPlateCode(plateInfo.getPlate().getCode());
                                return plate;
                            }).
                            collect(Collectors.toList()))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            node.setPlateList(plateList);
            FuTuBlockQueue.PLATE_BLOCK_QUEUE.offer(node);
        }
    }

}
