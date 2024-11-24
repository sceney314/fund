package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.FundCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FundCompanyDao {
    /**
     * 批量插入基金公司
     *
     * @param companyList 对象列表
     * @return int
     */
    int batchInsertCompany(@Param("companyList")List<FundCompany> companyList);

    /**
     * 根据编码获取公司列表
     *
     * @param codeList 公司编码列表
     * @return List
     */
    List<FundCompany> getByCodeList(@Param("codeList") List<String> codeList);

    /**
     * 按页获取公司列表
     *
     * @param startId 开始 ID
     * @return List
     */
    List<FundCompany> getCompanyWithPage(@Param("startId") Long startId);
}
