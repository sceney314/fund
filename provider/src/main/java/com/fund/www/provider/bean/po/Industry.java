package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Industry {
    private Integer id;

    private String industryId;

    private String industryName;

    private Integer isUse;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}