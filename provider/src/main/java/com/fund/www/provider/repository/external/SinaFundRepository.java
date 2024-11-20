package com.fund.www.provider.repository.external;

import com.fund.www.provider.bean.dto.FundCompanyDTO;
import com.fund.www.provider.bean.dto.FundTypeDTO;

import java.util.List;

public interface SinaFundRepository {
    /**
     * 获取基金类型
     *
     * @return List
     */
    List<FundTypeDTO> getFundTypeList();

    /**
     * 获取基金公司
     *
     * @return List
     */
    List<FundCompanyDTO> getFundCompanyList();
}
