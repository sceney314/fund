package com.fund.www.provider.observer.futu;

/**
 * 被观察者 富途牛牛
 */
public interface SubjectFuTu {
    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    void registerObserver(ObserverFuTu observer);

    /**
     * 删除观察者
     *
     * @param observer 观察者
     */
    void removeObserver(ObserverFuTu observer);
}
