package com.fund.www.provider.bean.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@ToString
public class QueryBySignalDateBO implements Serializable {
    /**
     * 信号日期
     */
    private LocalDate signalDate;
}
