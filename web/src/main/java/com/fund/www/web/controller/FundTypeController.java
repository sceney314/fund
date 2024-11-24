package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.provider.service.fund.FundCompanyService;
import com.fund.www.provider.service.fund.FundService;
import com.fund.www.provider.service.fund.FundSubjectService;
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

    @Resource
    private FundSubjectService fundSubjectService;

    @Resource
    private FundService fundService;

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

    @RequestMapping(value = "/initFundSubject", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> initFundSubject(){
        fundSubjectService.initSubject();
        return BaseResult.success();
    }

    @RequestMapping(value = "/initFund", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> initFund(){
        fundService.initFund();
        return BaseResult.success();
    }

    @RequestMapping(value = "/getFundInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> getFundInfo(){
        fundService.getFundInfo();
        return BaseResult.success();
    }


}
