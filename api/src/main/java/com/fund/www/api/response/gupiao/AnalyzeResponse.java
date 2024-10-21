package com.fund.www.api.response.gupiao;

import com.fund.www.api.common.AnalyzeResultTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 分析结果
 */
@Setter
@Getter
@ToString
public class AnalyzeResponse implements Serializable {
    /**
     * 结果 List
     */
    private List<Piao> list = new ArrayList<>();


    @Setter
    @Getter
    @ToString
    public static class Piao implements Serializable{
        /**
         * 信号日期
         */
        private LocalDate signalDate;

        /**
         * 证券代码
         */
        private String code;

        /**
         * 证券名字
         */
        private String piaoName;

        /**
         * 分析类型
         */
        private AnalyzeResultTypeEnum analyzeType;

        /**
         * 明细
         */
        private List<AnalyzeDetail> detailList = new ArrayList<>();
    }

    @Setter
    @Getter
    @ToString
    public static class AnalyzeDetail implements Serializable{
        /**
         * 导入日期
         */
        private String importDate;

        /**
         * 策略名字
         */
        private String ployName;

        /**
         * 趋势结果
         */
        private String tendency;

        /**
         * 委托价格
         */
        private String commissionPrice;

        /**
         * 目标价格
         */
        private String targetPrice;
    }

}
