package com.fund.www.provider.repository.external;

import com.fund.www.provider.bean.dto.FundTypeDTO;

import java.util.List;

public interface SinaFundRepository {
    /**
     * 获取基金类型
     *
     * @return List
     */
    List<FundTypeDTO> getFundTypeList();
}
