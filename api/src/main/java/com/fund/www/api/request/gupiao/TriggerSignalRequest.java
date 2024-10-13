package com.fund.www.api.request.gupiao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@ToString
public class TriggerSignalRequest implements Serializable {
    /**
     * 是否强制分析
     */
    private Integer forceAnalyze;

    /**
     * 文件名字
     */
    private String fileName;

    public void validate(){
        if (Objects.isNull(fileName) || fileName.trim().length() < 1){
            throw new IllegalArgumentException("参数 fileName 不能为空");
        }
    }
}
