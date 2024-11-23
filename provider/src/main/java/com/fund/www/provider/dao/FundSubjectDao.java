package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.FundSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FundSubjectDao {
    /**
     * 批量插入
     *
     * @param subjectList 主题列表
     * @return int
     */
    int batchInsertSubject(@Param("subjectList")List<FundSubject> subjectList);

    /**
     * 根据编码批量获取
     *
     * @param codeList 编码列表
     * @return List
     */
    List<FundSubject> getByCodeList(@Param("codeList")List<String> codeList);
}
