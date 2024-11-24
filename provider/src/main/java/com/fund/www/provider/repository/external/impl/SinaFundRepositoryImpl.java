package com.fund.www.provider.repository.external.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fund.www.provider.bean.bo.FundQueryParam;
import com.fund.www.provider.bean.dto.*;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.repository.external.SinaFundRepository;
import com.fund.www.provider.utils.HttpUtil;
import com.fund.www.provider.utils.MapUtils;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

    /**
     * 基金明细
     */
    private static final String URL_FUND_DETAIL = "https://finance.sina.com.cn/fund/quotes/{fundCode}/bc.shtml";

    /**
     * html 股票 ID
     */
    private static final String HTML_ID_STOCK = "fund_sdzc_table";

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

    @Override
    public List<StockDTO> queryFundStock(String fundCode) {
        String fundUrl = URL_FUND_DETAIL.replace("{fundCode}", fundCode);
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(fundUrl), 6000);
        } catch (IOException e) {
            throw new ServiceException("根据 URL[" + fundUrl + "]获取信息异常");
        }
        Element table = doc.getElementById(HTML_ID_STOCK);
        if (Objects.isNull(table)){
            return null;
        }
        Element tbody = table.getElementsByTag("tbody").first();

        List<Element> trList = new ArrayList<>();
        int size = tbody.childrenSize();
        while (size-- > 0){
            Element element = tbody.child(size);
            trList.add(element);
        }

        if (CollectionUtils.isEmpty(trList)){
            return null;
        }

        String reg = "[a-zA-Z]";

        return trList.stream().map(tr -> {
            StockDTO dto = new StockDTO();
            int tdSize = tr.childrenSize();
            for (int i = 0; i < tdSize; i++){
                Element td = tr.child(i);
                switch (i){
                    case 0:
                        Element a = td.child(0);
                        dto.setStockName(a.text());
                        String stockUrl = a.attr("href");
                        URL url = null;
                        try {
                            url = new URL(stockUrl);
                        } catch (MalformedURLException e) {
                            throw new ServiceException("解析 URL[" + stockUrl + "]异常");
                        }
                        String[] pathArr = url.getPath().split("/");
                        dto.setStockCode(pathArr[3].replace(reg, ""));
                        break;
                    case 1:
                        dto.setPrice(td.text());
                        break;
                    case 2:
                        dto.setScope(td.text());
                        break;
                    case 3:
                        dto.setPercent(td.text());
                        break;
                    case 4:
                        dto.setChangePercent(td.text());
                        break;
                    case 5:
                        dto.setStockNum(td.text());
                        break;
                    case 6:
                        dto.setNumChange(td.text());
                        break;
                    case 7:
                    default:
                        break;
                }
            }
            return dto;
        }).collect(Collectors.toList());
    }

}
