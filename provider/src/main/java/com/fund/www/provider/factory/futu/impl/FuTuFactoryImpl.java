package com.fund.www.provider.factory.futu.impl;

import com.fund.www.provider.bean.bo.StockPlateBO;
import com.fund.www.provider.bean.po.PlateGuPiao;
import com.fund.www.provider.bean.vo.PlateNode;
import com.fund.www.provider.common.constants.FuTuBlockQueue;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.factory.futu.FuTuFactory;
import com.fund.www.provider.observer.futu.ObserverFuTu;
import com.fund.www.provider.repository.futu.FuTuOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class FuTuFactoryImpl implements FuTuFactory {
    /**
     * 服务 API
     */
    private static final FuTuOpenApi API_INSTANCE = FuTuOpenApi.getInstance();

    @Autowired
    private void init(ObserverFuTu[] observerList){
        for (ObserverFuTu observer : observerList){
            API_INSTANCE.registerObserver(observer);
        }
    }


    @Override
    public void getAsyncOwnerPlate(List<StockPlateBO> boList) {
        triggerOwnerPlate(boList);
    }

    /**
     * 触发请求，返回请求序列号
     *
     * @param boList 股票列表
     * @return int
     */
    private int triggerOwnerPlate(List<StockPlateBO> boList){
        return API_INSTANCE.getOwnerPlate(boList);
    }

    @Override
    public List<PlateGuPiao> queryOwnerPlate(List<StockPlateBO> boList) {
        if (boList.size() > 200){
            throw new ServiceException("单次请求不能大于 200 个股票");
        }
        int requestSeq = triggerOwnerPlate(boList);
        PlateNode node = null;
        int idx = 40;
        while (idx-- > 0){
            Iterator<PlateNode> iterator = FuTuBlockQueue.PLATE_BLOCK_QUEUE.iterator();
            while (iterator.hasNext()){
                PlateNode item = iterator.next();
                if (Objects.equals(requestSeq, item.getSerialNo())){
                    node = item;
                    FuTuBlockQueue.PLATE_BLOCK_QUEUE.remove(item);
                    break;
                }
            }
            if (Objects.nonNull(node)){
                break;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (Objects.isNull(node)){
            throw new ServiceException("获取股票板块信息超时");
        }
        return node.getPlateList();
    }
}
