package com.fund.www.provider.observer;

public interface SubjectFund {
    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    void registerObserver(ObserverFund observer);

    /**
     * 删除观察者
     *
     * @param observer 观察者
     */
    void removeObserver(ObserverFund observer);


}
