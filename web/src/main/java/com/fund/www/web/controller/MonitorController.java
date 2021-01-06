package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Date: 2020-07-31 16:29
 * Copyright (C), 2015-2020
 */
@Controller
public class MonitorController {

    @RequestMapping(value = "/check")
    @ResponseBody
    public Result check(){
        return BaseResult.success();
    }

}
