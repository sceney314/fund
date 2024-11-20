package com.fund.www.provider.service.fund.impl;

import com.fund.www.provider.bean.dto.FundCompanyDTO;
import com.fund.www.provider.bean.po.FundCompany;
import com.fund.www.provider.dao.FundCompanyDao;
import com.fund.www.provider.repository.external.SinaFundRepository;
import com.fund.www.provider.service.fund.FundCompanyService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FundCompanyServiceImpl implements FundCompanyService {
    @Resource
    private SinaFundRepository sinaFundRepository;

    @Resource
    private FundCompanyDao fundCompanyDao;

    @Override
    public void initFundCompany() {
        List<FundCompanyDTO> dtoList = sinaFundRepository.getFundCompanyList();
        if (CollectionUtils.isEmpty(dtoList)){
            return;
        }

        List<FundCompany> list = fundCompanyDao.getByCodeList(dtoList.stream().map(FundCompanyDTO::getCode).collect(Collectors.toList()));
        Set<String> codeSet = CollectionUtils.isEmpty(list) ? new HashSet<>() : list.stream().map(FundCompany::getCompanyCode).collect(Collectors.toSet());
        List<FundCompany> companyList = dtoList.stream()
                .filter(d -> !codeSet.contains(d.getCode()))
                .map(dto -> new FundCompany(dto.getCode(), dto.getShortName(), dto.getName()))
                .filter(FundCompany.distinctPredicate(FundCompany::getCompanyCode))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(companyList)){
            return;
        }

        fundCompanyDao.batchInsertCompany(companyList);
    }
}
