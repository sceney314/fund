package com.fund.www.provider.service.fund.impl;

import com.fund.www.provider.bean.bo.FundQueryParam;
import com.fund.www.provider.bean.dto.FundDTO;
import com.fund.www.provider.bean.dto.StockDTO;
import com.fund.www.provider.bean.po.*;
import com.fund.www.provider.dao.FundDao;
import com.fund.www.provider.dao.StockDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.repository.external.SinaFundRepository;
import com.fund.www.provider.service.fund.FundCompanyService;
import com.fund.www.provider.service.fund.FundService;
import com.fund.www.provider.service.fund.FundSubjectService;
import com.fund.www.provider.service.fund.FundTypeService;
import com.fund.www.provider.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Long startId = 0L;
        Integer pageSize = 50;
        while (true){
            List<Fund> fundList = fundDao.queryFundFlow(startId, pageSize);
            if (CollectionUtils.isEmpty(fundList)){
                break;
            }
            asyncProcessStock(fundList);
            startId = fundList.stream().map(Fund::getId).max(Long::compareTo).orElseThrow(() -> new ServiceException("获取最大 ID 异常"));
        }
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
}
