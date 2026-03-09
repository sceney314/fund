package com.fund.www.provider.bean.vo;

import com.fund.www.provider.common.futu.FuTuSubjectEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ObserverFuTuNode<T> implements Serializable {
    /**
     * 学号
     */
    private int serialNo;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 主题
     */
    private FuTuSubjectEnum subject;

    public ObserverFuTuNode() {
    }

    public ObserverFuTuNode(int serialNo) {
        this.serialNo = serialNo;
    }

    public ObserverFuTuNode(int serialNo, FuTuSubjectEnum subject) {
        this.serialNo = serialNo;
        this.subject = subject;
    }

    public ObserverFuTuNode(int serialNo, T data, FuTuSubjectEnum subject) {
        this.serialNo = serialNo;
        this.data = data;
        this.subject = subject;
    }
}
