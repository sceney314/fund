package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.api.request.futu.QueryStockPlateRequest;

import com.fund.www.provider.service.futu.FuTuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/futu")
public class FuTuController  {
    @Resource
    private FuTuService fuTuService;


    @RequestMapping(value = "/pullKLine", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> initFundCompany(){
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        return BaseResult.success();
    }

    @RequestMapping(value = "/queryStockPlate", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> queryStockPlate(QueryStockPlateRequest request){
        request.validate();
        return BaseResult.success(fuTuService.queryOwnerPlate(Arrays.asList(request.getCodeList().trim().split(","))));
    }

}
