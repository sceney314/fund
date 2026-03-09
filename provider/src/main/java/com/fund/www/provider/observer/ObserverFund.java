package com.fund.www.provider.observer;

public interface ObserverFund {
    /**
     * 接收数据异步处理
     *
     * @param data 数据对象
     */
    <T> void recieveData(T data);

}
