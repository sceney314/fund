package com.fund.www.api.request.gupiao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@ToString
public class QueryBySignalDateRequest implements Serializable {
    /**
     * 信号日期
     */
    private String signalDate;

    public void validate(){
        if (Objects.isNull(signalDate)){
            throw new IllegalArgumentException("参数 signalDate 不能为空");
        }
    }
}
