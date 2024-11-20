package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.provider.service.fund.FundCompanyService;
import com.fund.www.provider.service.fund.FundTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/fundType")
public class FundTypeController {
    @Resource
    private FundTypeService fundTypeService;

    @Resource
    private FundCompanyService fundCompanyService;

    @RequestMapping(value = "/initFundType", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> initFundType(){
        fundTypeService.initFundType();
        return BaseResult.success();
    }

    @RequestMapping(value = "/initFundCompany", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> initFundCompany(){
        fundCompanyService.initFundCompany();
        return BaseResult.success();
    }


}
