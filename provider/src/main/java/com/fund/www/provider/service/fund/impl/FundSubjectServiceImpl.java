package com.fund.www.provider.service.fund.impl;

import com.fund.www.provider.bean.dto.FundSubjectDTO;
import com.fund.www.provider.bean.po.FundSubject;
import com.fund.www.provider.dao.FundSubjectDao;
import com.fund.www.provider.repository.external.SinaFundRepository;
import com.fund.www.provider.service.fund.FundSubjectService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FundSubjectServiceImpl implements FundSubjectService {
    @Resource
    private SinaFundRepository sinaFundRepository;

    @Resource
    private FundSubjectDao fundSubjectDao;

    @Override
    public void initSubject() {
        List<FundSubjectDTO> dtoList = sinaFundRepository.getFundSubjectList();
        if (CollectionUtils.isEmpty(dtoList)){
            return;
        }

        List<FundSubject> subjectList = fundSubjectDao.getByCodeList(dtoList.stream().map(FundSubjectDTO::getCode).collect(Collectors.toList()));
        Set<String> codeSet = CollectionUtils.isEmpty(subjectList) ?  new HashSet<>() : subjectList.stream().map(FundSubject::getSubjectCode).collect(Collectors.toSet());
        List<FundSubject> insertList = dtoList.stream()
                .filter(dto -> !codeSet.contains(dto.getCode()))
                .map(dto -> new FundSubject(dto.getCode(), dto.getName()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(insertList)){
            return;
        }

        fundSubjectDao.batchInsertSubject(insertList);
    }

    @Override
    public List<FundSubject> getAllSubject() {
        return fundSubjectDao.getAllSubject();
    }
}
