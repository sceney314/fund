package com.fund.www.provider.service.fund.impl;

import com.fund.www.provider.bean.bo.FundQueryParam;
import com.fund.www.provider.bean.dto.FundDTO;
import com.fund.www.provider.bean.po.Fund;
import com.fund.www.provider.bean.po.FundCompany;
import com.fund.www.provider.bean.po.FundSubject;
import com.fund.www.provider.bean.po.FundType;
import com.fund.www.provider.dao.FundDao;
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

}
