package com.fund.www.api.request.gupiao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@ToString
public class QueryBySignalDateRequest implements Serializable {
    /**
     * 信号日期
     */
    private LocalDate signalDate;
}
