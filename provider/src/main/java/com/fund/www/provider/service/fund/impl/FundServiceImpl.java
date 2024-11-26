package com.fund.www.provider.service.fund.impl;

import com.fund.www.provider.bean.bo.FundQueryParam;
import com.fund.www.provider.bean.dto.FundDTO;
import com.fund.www.provider.bean.dto.StockDTO;
import com.fund.www.provider.bean.po.*;
import com.fund.www.provider.dao.FundDao;
import com.fund.www.provider.dao.StockDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.repository.external.SinaFundRepository;
import com.fund.www.provider.service.fund.*;
import com.fund.www.provider.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FundServiceImpl implements FundService {
    @Resource
    private SinaFundRepository sinaFundRepository;

    @Resource
    private FundCompanyService fundCompanyService;

    @Resource
    private FundSubjectService fundSubjectService;

    @Resource
    private FundTypeService fundTypeService;

    @Resource
    private FundStockService fundStockService;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private FundDao fundDao;

    @Resource
    private StockDao stockDao;

    @Override
    public void initFund() {
        List<FundCompany> companyList = fundCompanyService.getAllCompany();
        List<FundSubject> subjectList = fundSubjectService.getAllSubject();
        List<FundType> typeList = fundTypeService.getAllType();
        Map<String, String> companyMap = CollectionUtils.isEmpty(companyList) ? new HashMap<>() : companyList.stream().collect(Collectors.toMap(FundCompany::getCompanyName, FundCompany::getCompanyCode, (v1, v2) -> v1));
        Map<String, String> subjectMap = CollectionUtils.isEmpty(subjectList) ? new HashMap<>() : subjectList.stream().collect(Collectors.toMap(FundSubject::getSubjectName, FundSubject::getSubjectCode, (v1, v2) -> v1));
        Map<String, String> typeMap = CollectionUtils.isEmpty(typeList) ? new HashMap<>() : typeList.stream()
                .filter(t -> StringUtils.isNotEmpty(t.getParentCode()))
                .collect(Collectors.toMap(FundType::getSinaTypeCode, FundType::getTypeName, (v1, v2) -> v1));

        typeMap.forEach((typeCode, typeName) -> asyncProcessFund(typeCode, subjectMap, companyMap));
    }

    /**
     * 异步处理
     *
     * @param typeCode 类型编码
     * @param subjectMap 主题编码
     * @param companyMap 公司类型
     */
    private void asyncProcessFund(String typeCode, Map<String, String> subjectMap, Map<String, String> companyMap){
        subjectMap.forEach((subjectName, subjectCode) -> threadPoolTaskExecutor.submit(() -> {
            int idx = 1;
            while (true){
                FundQueryParam param = new FundQueryParam();
                param.setPage(idx);
                param.setTypeCodeList(Collections.singletonList(typeCode));
                param.setSubjectCode(subjectCode);
                List<FundDTO> dtoList = sinaFundRepository.getFundList(param);
                if (CollectionUtils.isEmpty(dtoList)){
                    break;
                }
                List<String> codeList = fundDao.getExistCodeList(dtoList.stream().map(FundDTO::getCode).collect(Collectors.toList()));
                List<Fund> fundList = dtoList.stream()
                        .filter(dto -> !codeList.contains(dto.getCode()))
                        .map(dto -> {
                            Fund fund = new Fund();
                            fund.setFundCode(dto.getCode());
                            fund.setFundName(dto.getName());
                            fund.setCompanyCode(companyMap.getOrDefault(dto.getCompanyName(), ""));
                            fund.setCompanyName(dto.getCompanyName());
                            fund.setSubjectName(dto.getSubjectName());
                            fund.setSubjectCode(subjectMap.getOrDefault(dto.getSubjectName(), ""));
                            fund.setFundSize(dto.getFundSize());
                            fund.setTypeCodeOne(dto.getCodeOne());
                            fund.setTypeCodeTwo(dto.getTypeTwo());
                            fund.setTypeCodeThree(dto.getTypeThree());
                            fund.setNetValue(dto.getNetValue());
                            fund.setTotalNetValue(dto.getSumNet());
                            fund.setOneMonth(dto.getOneMonth());
                            fund.setThreeMonth(dto.getThreeMonth());
                            fund.setSixMonth(dto.getSixMonth());
                            fund.setOneYear(dto.getOneYear());
                            fund.setThreeYear(dto.getThreeYear());
                            fund.setFiveYear(dto.getFiveYear());
                            fund.setToThisDay(dto.getAllYear());
                            return fund;
                        })
                        .collect(Collectors.toList());
                fundDao.insertFundList(fundList);
                idx++;
            }
        }));
    }

    @Override
    public void getFundInfo() {
        List<Fund> fundList =  queryAllFund();
        if (CollectionUtils.isEmpty(fundList)){
            return;
        }
        asyncProcessStock(fundList);
    }

    /**
     * 查询全部基金
     *
     * @return  List
     */
    private List<Fund> queryAllFund(){
        List<Fund> fundList = new ArrayList<>();
        Long startId = 0L;
        Integer pageSize = 50;
        while (true){
            List<Fund> list = fundDao.queryFundFlow(startId, pageSize);
            if (CollectionUtils.isEmpty(list)){
                break;
            }
            fundList.addAll(list);
            startId = list.stream().map(Fund::getId).max(Long::compareTo).orElseThrow(() -> new ServiceException("获取最大 ID 异常"));
        }
        return fundList;
    }

    /**
     * 异步处理基金股票
     *
     * @param fundList 基金列表
     */
    private void asyncProcessStock(List<Fund> fundList){
        threadPoolTaskExecutor.submit(() -> {
            for (Fund fund : fundList){
                List<StockDTO> dtoList = sinaFundRepository.queryFundStock(fund.getFundCode());
                if (CollectionUtils.isEmpty(dtoList)){
                    continue;
                }

                List<Stock> stockList = stockDao.queryByCodeList(dtoList.stream().map(StockDTO::getStockCode).collect(Collectors.toList()), fund.getFundCode());
                Map<String, Stock> stockMap = CollectionUtils.isEmpty(stockList) ? new HashMap<>() : stockList.stream().collect(Collectors.toMap(Stock::getStockCode, Function.identity(), (v1, v2) -> v1));
                List<Stock> dataList = dtoList.stream()
                        .map(dto -> {
                            Stock stock = new Stock();
                            stock.setFundCode(fund.getFundCode());
                            stock.setStockCode(dto.getStockCode());
                            stock.setStockName(dto.getStockName());
                            stock.setFundPercent(dto.getPercent());
                            stock.setChangePercent(dto.getChangePercent());
                            stock.setHoldNum(dto.getStockNum());
                            stock.setHoldChangeNum(dto.getNumChange());
                            return stock;
                        })
                        .collect(Collectors.toList());
                List<Stock> insertList = dataList.stream().filter(d -> !stockMap.containsKey(d.getStockCode())).collect(Collectors.toList());
                List<Stock> updateList = dataList.stream().filter(d -> stockMap.containsKey(d.getStockCode())).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(insertList)){
                    stockDao.insertStockList(insertList);
                }

                if (CollectionUtils.isNotEmpty(updateList)){
                    updateList.forEach(stock -> stockDao.updateFundStock(stock));
                }
            }
        });
    }

    @Override
    public String analyzeStockByFund() {
        List<Fund> fundList =  queryAllFund();
        Map<String, Fund> fundMap = CollectionUtils.isEmpty(fundList) ? new HashMap<>() : fundList.stream().collect(Collectors.toMap(Fund::getFundCode, Function.identity(), (v1, v2) -> v1));
        List<Stock> stockList = fundStockService.queryAllStock();
        if (CollectionUtils.isEmpty(stockList)){
            return "";
        }

        Map<String, List<Stock>> stockMap = stockList.stream().collect(Collectors.groupingBy(Stock::getStockCode));
        List<List<Stock>> list = new ArrayList<>(stockMap.values());
        list.sort((o1, o2) -> Integer.compare(o2.size(), o1.size()));

        StringBuilder sb = new StringBuilder();
        sb.append("+---------------+-----------+-----------+-----------+-----------+-----------+-----------+\n");
        sb.append("|\t证券代码\t\t|\t证券名称\t|\t基金数量\t|\t数量变化\t|\t基金编码\t|\t基金占比\t|\t占比变化\t|\n");
        sb.append("+---------------+-----------+-----------+-----------+-----------+-----------+-----------+\n");

        for (List<Stock> stockItem : list){
            for (int i = 0; i < stockItem.size(); i++){
                Stock stock = stockItem.get(i);
                if (i == 0){
                    sb.append("|\t" + stock.getStockCode() + "\t")
                            .append("|\t" + stock.getStockName()+ "\t")
                            .append("|\t" + stock.getHoldNum() + "\t")
                            .append("|\t" + stock.getHoldChangeNum() + "\t")
                            .append("|\t" + stock.getFundCode() + "\t")
                            .append("|\t" + stock.getFundPercent() + "\t")
                            .append("|\t" + stock.getChangePercent() + "\t|\n");
                }else{
                    sb.append("|\t\t\t\t\t\t\t")
                            .append("|\t" + stock.getHoldNum() + "\t")
                            .append("|\t" + stock.getHoldChangeNum() + "\t")
                            .append("|\t" + stock.getFundCode() + "\t")
                            .append("|\t" + stock.getFundPercent() + "\t")
                            .append("|\t" + stock.getChangePercent() + "\t|\n");
                }
            }

        }
        sb.append("+---------------+-----------+-----------+-----------+-----------+-----------+-----------+\n");
        return sb.toString();
    }

}
