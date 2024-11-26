package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.service.fund.FundCompanyService;
import com.fund.www.provider.service.fund.FundService;
import com.fund.www.provider.service.fund.FundSubjectService;
import com.fund.www.provider.service.fund.FundTypeService;
import com.fund.www.provider.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @RequestMapping(value = "/analyzeStockByFund", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> analyzeStockByFund(){
        String content = fundService.analyzeStockByFund();
        if (StringUtils.isEmpty(content)){
            throw new ServiceException("分析基金重仓失败");
        }
        String file = "/Users/lovelife/Documents/data-master/bin/fund" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".txt";
        Path pathToFile = Paths.get(file);

        try {
            // 创建文件，如果文件已经存在则不进行任何操作
            Files.createFile(pathToFile);
            Files.write(pathToFile, content.getBytes(StandardCharsets.UTF_8)); // 写入文件
        } catch (Exception e) {
            throw new ServiceException("结果写入文本失败");
        }
        return BaseResult.success();
    }


}
