package com.fund.www.provider.repository.external.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fund.www.provider.bean.bo.FundQueryParam;
import com.fund.www.provider.bean.dto.*;
import com.fund.www.provider.repository.external.SinaFundRepository;
import com.fund.www.provider.utils.HttpUtil;
import com.fund.www.provider.utils.MapUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SinaFundRepositoryImpl implements SinaFundRepository {
    /**
     * 基金类型地址
     */
    private static final String URL_FUND_TYPE = "https://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getFundTypeList";

    /**
     * 基金公司
     */
    private static final String URL_FUND_COMPANY = "https://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getCompanyList";

    /**
     * 基金主题
     */
    private static final String URL_FUND_SUBJECT = "https://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getSubjectList";

    /**
     * 查询基金列表
     */
    private static final String URL_FUND = "https://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getFundFilterAll";

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

    @Override
    public List<FundCompanyDTO> getFundCompanyList() {
        SinaFundResultDTO result = JSON.parseObject(HttpUtil.getRequest(URL_FUND_COMPANY), SinaFundResultDTO.class);
        Map<String, List<FundCompanyDTO>> companyMap = result.getDataMap(new TypeReference<Map<String, List<FundCompanyDTO>>>(){});
        if (MapUtils.isEmpty(companyMap)){
            return null;
        }
        return companyMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(FundCompanyDTO::getCode))), ArrayList<FundCompanyDTO>::new));
    }

    @Override
    public List<FundSubjectDTO> getFundSubjectList() {
        SinaFundResultDTO result = JSON.parseObject(HttpUtil.getRequest(URL_FUND_SUBJECT), SinaFundResultDTO.class);
        return result.getDataList(FundSubjectDTO.class);
    }

    @Override
    public List<FundDTO> getFundList(FundQueryParam param) {
        String url = URL_FUND + "?" + param.generateParam();
        SinaFundResultDTO result = JSON.parseObject(HttpUtil.getRequest(url), SinaFundResultDTO.class);
        FundPageDTO data = result.getDataObject(FundPageDTO.class);
        if (CollectionUtils.isEmpty(data.getData())){
            return null;
        }
        return data.getData();
    }
}
