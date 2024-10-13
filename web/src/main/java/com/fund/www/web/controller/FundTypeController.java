package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.api.bo.AddFundTypeBo;
import com.fund.www.provider.service.business.FundTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fundType")
public class FundTypeController {

    @Autowired
    private FundTypeService service;

    @RequestMapping(value = "/addFundType", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> addFundType(AddFundTypeBo bo){
        service.addFundType(bo.getCode(), bo.getName());
        return BaseResult.success();
    }
}
