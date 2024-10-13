package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.api.bo.TestBo;
import com.fund.www.api.vo.TestVo;
import com.fund.www.provider.bean.po.FundType;
import com.fund.www.provider.dao.FundTypeDao;
import com.fund.www.provider.service.dubbo.TestDubboService;
import com.fund.www.provider.utils.RedisUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 测试
 * <p>
 * Date: 2020-07-31 19:59
 * Copyright (C), 2015-2020
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger log = LogManager.getLogger(TestController.class);

    @Autowired
    private FundTypeDao fundTypeDao;

    @Autowired
    private TestDubboService testService;


    @RequestMapping(value = "/getFundType", method = RequestMethod.POST)
    @ResponseBody
    public Result<FundType> addGroup(){
        return BaseResult.success(fundTypeDao.getById(1));
    }


    @RequestMapping(value = "/testCache")
    @ResponseBody
    public Result<?> testCache(){
        String key = "justTestInApplication";
        try {
            RedisUtils.setWithExpire(key, LocalDateTime.now().toString(), 3);
            int i = 5;
            while (i-- > 0){
                System.out.println("i=" + i + ", value=" + RedisUtils.get(key) + ", " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return BaseResult.success();
    }

    @RequestMapping(value = "/dubbo")
    @ResponseBody
    public Result<TestVo> dubbo(String msg){
        TestBo bo = new TestBo();
        bo.setMsg(msg);
        return testService.test(bo);
    }

//    @RequestMapping(value = "/mqSync")
//    @ResponseBody
//    public Result<?> mqSync(){
//        MessageBo<SyncTestBo> bo = new MessageBo<>();
//        SyncTestBo testBo = new SyncTestBo();
//        testBo.setDate(DateUtils.date2String());
//        testBo.setContent("this is sync mq test! date " + testBo.getDate());
//        bo.setT(testBo);
//        bo.setBizId(System.currentTimeMillis());
//        bo.setTopic("SYNC_MQ_TEST");
//        bo.setTag("SYNC_TAG");
//        bo.setKey(bo.getBizId() + "");
//        log.info("发送 MQ:{}", bo);
//        if (MQUtils.sendCurrentMsg(bo)){
//            return BaseResult.success();
//        }
//
//        return BaseResult.fail();
//    }
}
