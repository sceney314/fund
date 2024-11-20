package com.fund.www.provider.service.fund.impl;

import com.fund.www.provider.bean.dto.FundTypeDTO;
import com.fund.www.provider.bean.po.FundType;
import com.fund.www.provider.dao.FundTypeDao;
import com.fund.www.provider.repository.external.SinaFundRepository;
import com.fund.www.provider.service.fund.FundTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FundTypeServiceImpl implements FundTypeService {
    @Resource
    private SinaFundRepository sinaFundRepository;

    @Autowired
    private FundTypeDao fundTypeDao;

    @Override
    public void initFundType() {
        List<FundTypeDTO> dtoList = sinaFundRepository.getFundTypeList();
        if (CollectionUtils.isEmpty(dtoList)){
            return;
        }

        // 删除数据
        fundTypeDao.clearFundTypeData();

        // 重新插入
        List<FundType> typeList = dtoList.stream()
                .map(dto -> FundType.getInstance(dto.getTypeCode(), StringUtils.defaultIfBlank(dto.getParentCode(), ""), dto.getName()))
                .filter(FundType.distinctPredicate(FundType::getSinaTypeCode))
                .collect(Collectors.toList());
        fundTypeDao.batchInsertFundType(typeList);
    }


}
