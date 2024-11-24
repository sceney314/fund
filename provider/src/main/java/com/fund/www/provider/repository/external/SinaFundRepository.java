package com.fund.www.provider.repository.external;

import com.fund.www.provider.bean.bo.FundQueryParam;
import com.fund.www.provider.bean.dto.FundCompanyDTO;
import com.fund.www.provider.bean.dto.FundDTO;
import com.fund.www.provider.bean.dto.FundSubjectDTO;
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

    /**
     * 获取基金主题
     *
     * @return List
     */
    List<FundSubjectDTO> getFundSubjectList();

    /**
     * 查询基金列表
     *
     * @param param 查询对象
     * @return List
     */
    List<FundDTO> getFundList(FundQueryParam param);
}
