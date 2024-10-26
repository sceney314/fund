package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.provider.service.caipiao.CaiPiaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/caiPiao")
public class CaiPiaoController {
    @Resource
    private CaiPiaoService caiPiaoService;

    @RequestMapping(value = "/triggerShuangSeQiuInit", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> triggerShuangSeQiuInit(){
        caiPiaoService.triggerShuangSeQiuInit();
        return BaseResult.success();
    }

    @RequestMapping(value = "/triggerShuangSeQiu", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> triggerShuangSeQiu(){
        caiPiaoService.triggerShuangSeQiu();
        return BaseResult.success();
    }
}
