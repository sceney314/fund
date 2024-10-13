package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Fund {
    private Integer id;

    private String fundId;

    private String fundCode;

    private String fundTypeId;

    private String fundName;

    private Long fundSize;

    private Integer netValue;

    private Integer oneMonth;

    private Integer threeMonth;

    private Integer sixMonth;

    private Integer oneYear;

    private Integer threeYear;

    private Integer toThisDay;

    private String riskLevel;

    private Integer minValue;

    private Integer canBuy;

    private Integer isUse;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String manager;
}