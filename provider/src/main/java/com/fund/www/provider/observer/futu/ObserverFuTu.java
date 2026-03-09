package com.fund.www.provider.observer.futu;

import com.fund.www.provider.bean.vo.ObserverFuTuNode;
import com.fund.www.provider.common.futu.FuTuSubjectEnum;

public interface ObserverFuTu{
    /**
     * 观察者类型
     *
     * @return FuTuSubjectEnum
     */
    FuTuSubjectEnum subject();

    /**
     * 接收数据异步处理
     *
     * @param node 数据对象
     */
    <T> void recieveData(ObserverFuTuNode<T> node);
}
