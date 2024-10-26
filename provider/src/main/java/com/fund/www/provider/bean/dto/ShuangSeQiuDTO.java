package com.fund.www.provider.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class ShuangSeQiuDTO implements Serializable {
    /**
     * 状态
     */
    private Integer state;

    /**
     * 消息
     */
    private String message;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pageNum;

    /**
     * 当前页数
     */
    private Integer pageNo;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 结果列表
     */
    private List<ShuangSeQiuDetail> result;

    @Setter
    @Getter
    @ToString
    public static class ShuangSeQiuDetail implements Serializable{
        /**
         * 彩票名字
         */
        @JSONField(name = "name")
        private String caiPiaoName;

        /**
         * 彩票期号
         */
        @JSONField(name = "code")
        private String caiPiaoCode;

        /**
         * 开奖日期
         */
        private String date;

        /**
         * 红球
         */
        private String red;

        /**
         * 蓝球
         */
        private String blue;
    }

}
