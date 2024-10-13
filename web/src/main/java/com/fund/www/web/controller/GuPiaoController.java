package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.api.request.gupiao.QueryBySignalDateRequest;
import com.fund.www.api.request.gupiao.TriggerSignalRequest;
import com.fund.www.provider.bean.model.ImportModel;
import com.fund.www.provider.bean.po.GuPiao;
import com.fund.www.provider.common.constants.FundConst;
import com.fund.www.provider.service.gupiao.GuPiaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 股票控制器
 */
@Slf4j
@Controller
@RequestMapping("/gupiao")
public class GuPiaoController {
    @Resource
    private GuPiaoService guPiaoService;

    @RequestMapping(value = "/queryBySignalDate", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> queryBySignalDate(QueryBySignalDateRequest request){
        return BaseResult.success(request);
    }

    @RequestMapping(value = "/triggerSignal", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> triggerSignal(TriggerSignalRequest request){
        request.validate();

        String file = "/Users/lovelife/Documents/data-master/bin/" + request.getFileName();
        String regex = "^[A-Za-z0-9]+$";
        List<GuPiao> piaoList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] item = line.trim().replace(" ", "").split("\\|");
                if (item.length > 2 && Pattern.matches(regex, item[1])){
                    GuPiao piao = new GuPiao();
                    piao.setPiaoCode(item[1].replace("sz", "").replace("sh", ""));
                    if (piao.getPiaoCode().equals("000001") || piao.getPiaoCode().equals("399001")){
                        continue;
                    }
                    piao.setPiaoName(item[2]);
                    piao.setSignalDate(LocalDate.parse(item[3]));
                    piao.setCommissionPrice(item[4]);
                    piao.setTargetPrice(item[5]);
                    piao.setPloyType(Integer.parseInt(item[6]));
                    piao.setPloyName(item[7]);
                    piao.setTendency(item[8]);
                    piaoList.add(piao);
                }
            }
        } catch (Exception e) {
            log.error("解析文件异常", e);
        }
        if (CollectionUtils.isNotEmpty(piaoList)){
            ImportModel model = new ImportModel(piaoList.get(0).getSignalDate(), piaoList.get(0).getPloyType(), piaoList.get(0).getPloyName());
            model.setPiaoList(piaoList);
            model.setForceAnalyze(FundConst.YES_INT.equals(request.getForceAnalyze()));
            guPiaoService.triggerSignal(model);
        }

        return BaseResult.success();
    }
}
