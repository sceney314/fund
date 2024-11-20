package com.fund.www.provider.repository.external.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fund.www.provider.bean.dto.FundTypeDTO;
import com.fund.www.provider.bean.dto.FundTypeItemDTO;
import com.fund.www.provider.bean.dto.SinaFundResultDTO;
import com.fund.www.provider.repository.external.SinaFundRepository;
import com.fund.www.provider.utils.HttpUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class SinaFundRepositoryImpl implements SinaFundRepository {
    /**
     * 基金类型地址
     */
    private static final String URL_FUND_TYPE = "https://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getFundTypeList";

    @Override
    public List<FundTypeDTO> getFundTypeList() {
        SinaFundResultDTO result = JSON.parseObject(HttpUtil.getRequest(URL_FUND_TYPE), SinaFundResultDTO.class);
        Map<String, FundTypeItemDTO> fundMap = result.getDataMap(new TypeReference<Map<String, FundTypeItemDTO>>(){});
        if (Objects.isNull(fundMap) || fundMap.isEmpty()){
            return null;
        }

        List<FundTypeDTO> typeList = new ArrayList<>();
        fundMap.forEach((parentCode, item) -> {
            FundTypeDTO dto = new FundTypeDTO(item.getName(), parentCode);
            typeList.add(dto);
            if (Objects.nonNull(item.getTypeMap()) && !item.getTypeMap().isEmpty()){
                item.getTypeMap().forEach((code, name)  -> {
                    FundTypeDTO type = new FundTypeDTO(name, code);
                    type.setParentCode(parentCode);
                    typeList.add(type);
                });
            }
        });
        return typeList;
    }
}
