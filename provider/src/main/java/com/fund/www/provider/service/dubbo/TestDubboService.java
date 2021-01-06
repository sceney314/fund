package com.fund.www.provider.service.dubbo;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.api.bo.TestBo;
import com.fund.www.api.service.ITestService;
import com.fund.www.api.vo.TestVo;
import org.springframework.stereotype.Service;

/**
 * Date: 2020-08-03 09:51
 * Copyright (C), 2015-2020
 */
@Service
public class TestDubboService implements ITestService {

    @Override
    public Result<TestVo> test(TestBo bo) {
        return BaseResult.success(new TestVo(bo.getMsg()));
    }
}
