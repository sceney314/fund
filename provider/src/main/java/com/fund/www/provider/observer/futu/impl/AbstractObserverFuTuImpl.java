package com.fund.www.provider.observer.futu.impl;

import com.fund.www.provider.bean.vo.ObserverFuTuNode;
import com.fund.www.provider.observer.futu.ObserverFuTu;

public abstract class AbstractObserverFuTuImpl implements ObserverFuTu {

    /**
     * 处理数据
     *
     * @param data 具体数据对象
     * @param serialNo 请求序号
     */
    protected abstract <T> void processData(T data, int serialNo);

    @Override
    public <T> void recieveData(ObserverFuTuNode<T> node) {
        processData(node.getData(), node.getSerialNo());
    }
}
