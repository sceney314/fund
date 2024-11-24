package com.fund.www.provider.service.fund;

import com.fund.www.provider.bean.po.FundSubject;

import java.util.List;

public interface FundSubjectService {
    /**
     * 初始化主题
     */
    void initSubject();

    /**
     * 获取全部主题
     *
     * @return List
     */
    List<FundSubject> getAllSubject();
}
