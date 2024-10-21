package com.fund.www.web.controller;

import com.fund.www.api.base.BaseResult;
import com.fund.www.api.base.Result;
import com.fund.www.api.common.AnalyzeResultTypeEnum;
import com.fund.www.api.request.gupiao.QueryBySignalDateRequest;
import com.fund.www.api.request.gupiao.TriggerSignalRequest;
import com.fund.www.api.response.gupiao.AnalyzeResponse;
import com.fund.www.provider.bean.model.ImportModel;
import com.fund.www.provider.bean.po.GuPiao;
import com.fund.www.provider.bean.po.GuPiaoAnalyzeResult;
import com.fund.www.provider.common.constants.FundConst;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.service.gupiao.GuPiaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        System.out.println(request.toString());
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


    @RequestMapping(value = "/showAnalyzeList", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> showAnalyzeList(){
        List<GuPiaoAnalyzeResult> resultList = guPiaoService.showAnalyze();
        if (CollectionUtils.isEmpty(resultList)){
            BaseResult.success();
        }
        return BaseResult.success(formatAnalyzeResponse(resultList));
    }

    @RequestMapping(value = "/showAnalyzeWithFile", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> showAnalyzeWithFile(){
        List<GuPiaoAnalyzeResult> resultList = guPiaoService.showAnalyze();
        String file = "/Users/lovelife/Documents/data-master/bin/analyze" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".txt";
        Path pathToFile = Paths.get(file);

        try {
            // 创建文件，如果文件已经存在则不进行任何操作
            Files.createFile(pathToFile);
            Files.write(pathToFile, formatAnalyzeResponse2String(resultList).getBytes(StandardCharsets.UTF_8)); // 写入文件
        } catch (Exception e) {
            log.error("结果写入文本失败", e);
            throw new ServiceException("结果写入文本失败");
        }
        return BaseResult.success();
    }

    @RequestMapping(value = "/showAnalyze", method = RequestMethod.GET)
    @ResponseBody
    public Result<?> showAnalyze(){
        List<GuPiaoAnalyzeResult> resultList = guPiaoService.showAnalyze();
        return BaseResult.success(formatAnalyzeResponse2String(resultList));
    }

    private String formatAnalyzeResponse2String(List<GuPiaoAnalyzeResult> resultList){
        if (CollectionUtils.isEmpty(resultList)){
            return "";
        }
        String[] tabArr = new String[]{"证券代码", "证券名称", "分析方式", "策略名称", "信号日期", "短线趋势", "委托价格", "目标价格"};
        String tab = "+" + StringUtils.rightPad("-", 13, "-") + "+";
        String tableHead = "+-----------+-----------+-----------------------+---------------+-----------+---------------+-----------+-----------+";
        AnalyzeResponse response = formatAnalyzeResponse(resultList);
        StringBuilder sb = new StringBuilder();
        sb.append(tableHead).append("\n");
        sb.append("|\t证券代码\t|\t证券名称\t|\t\t分析方式\t\t\t|\t策略名称\t\t|\t信号日期\t|\t短线趋势\t\t|\t委托价格\t|\t目标价格\t|");
        sb.append("\n").append(tableHead).append("\n");
        for (AnalyzeResponse.Piao piao : response.getList()) {
            sb.append("|").append("\t" + piao.getCode() + "\t").append("|")
                    .append("\t" + piao.getPiaoName() + "\t").append("|")
                    .append("\t" + piao.getAnalyzeType().getMsg() + "\t").append("|");
            for (int i = 0 ; i < piao.getDetailList().size(); i++){
                AnalyzeResponse.AnalyzeDetail detail = piao.getDetailList().get(i);
                if (i == 0){
                    sb.append("\t" + detail.getImportDate() + "\t").append("|");
                    sb.append("\t" + detail.getPloyName() + "\t").append("|");
                    sb.append("\t" + detail.getTendency() + "\t").append("|");
                    sb.append("\t" + detail.getCommissionPrice() + "\t").append("|");
                    sb.append("\t" + detail.getTargetPrice() + "\t").append("|");
                    sb.append("\n");
                }else{
                    sb.append("|").append("\t\t\t\t\t\t\t\t\t\t\t\t").append("|");
                    sb.append("\t" + detail.getImportDate() + "\t").append("|");
                    sb.append("\t" + detail.getPloyName() + "\t").append("|");
                    sb.append("\t" + detail.getTendency() + "\t").append("|");
                    sb.append("\t" + detail.getCommissionPrice() + "\t").append("|");
                    sb.append("\t" + detail.getTargetPrice() + "\t").append("|");
                    sb.append("\n");
                }
            }
        }
        sb.append(tableHead).append("\n");
        return sb.toString();
    }

    /**
     * 格式化返回参数
     *
     * @param resultList 结果列表
     * @return AnalyzeResponse
     */
    private AnalyzeResponse formatAnalyzeResponse(List<GuPiaoAnalyzeResult> resultList){
        AnalyzeResponse response = new AnalyzeResponse();
        if (CollectionUtils.isEmpty(resultList)){
            return response;
        }

        Map<String, Map<Integer, List<GuPiaoAnalyzeResult>>> resultMap = resultList.stream().collect(Collectors.groupingBy(GuPiaoAnalyzeResult::getPiaoCode, Collectors.groupingBy(GuPiaoAnalyzeResult::getAnalyzeType)));
        resultMap.forEach((code, itemMap) -> itemMap.forEach((type, itemList) -> {
            itemList.sort((o1, o2) -> {
                int val = o2.getAnalyzeType().compareTo(o1.getAnalyzeType());
                return val != 0 ? val : o2.getPloyName().compareTo(o1.getPloyName());
            });
            AnalyzeResponse.Piao piao = new AnalyzeResponse.Piao();
            piao.setCode(code);
            piao.setAnalyzeType(AnalyzeResultTypeEnum.getInstance(type));
            piao.setPiaoName(itemList.get(0).getPiaoName());
            piao.setSignalDate(itemList.get(0).getSignalDate());
            piao.setDetailList(itemList.stream().map(item -> {
                AnalyzeResponse.AnalyzeDetail detail = new AnalyzeResponse.AnalyzeDetail();
                detail.setImportDate(item.getImportDate().toString());
                detail.setPloyName(item.getPloyName());
                detail.setTendency(item.getTendency());
                detail.setCommissionPrice(item.getCommissionPrice());
                detail.setTargetPrice(item.getTargetPrice());
                return detail;
            }).collect(Collectors.toList()));

            response.getList().add(piao);
        }));

        response.getList().sort((o1, o2) -> {
            int type = o2.getAnalyzeType().getCode().compareTo(o1.getAnalyzeType().getCode());
            return type != 0 ? type : Integer.compare(o2.getDetailList().size(), o1.getDetailList().size());
        });

        response.getList().forEach(piao -> piao.getDetailList().sort((o1, o2) -> LocalDate.parse(o2.getImportDate()).compareTo(LocalDate.parse(o1.getImportDate()))));
        return response;
    }
}
