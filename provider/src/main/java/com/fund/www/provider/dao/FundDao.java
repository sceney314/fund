package com.fund.www.provider.dao;

import com.fund.www.provider.bean.po.Fund;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FundDao {
    /**
     * 批量插入基金
     *
     * @param fundList 基金列表
     * @return int
     */
    int insertFundList(@Param("fundList")List<Fund> fundList);

    /**
     * 根据基金编码获取基金列表
     *
     * @param codeList 基金编码基金列表
     * @return List
     */
    List<Fund> getByCodeList(@Param("codeList")List<String> codeList);

    /**
     * 获取已存在的基金编码列表
     *
     * @param codeList 基金编码基金列表
     * @return List
     */
    List<String> getExistCodeList(@Param("codeList")List<String> codeList);

    /**
     * 使用瀑布流方式，批量获取基金
     *
     * @param startId 开始 ID
     * @param pageSize 每页大小
     * @return List
     */
    List<Fund> queryFundFlow(@Param("startId") Long startId, @Param("pageSize") Integer pageSize);

}