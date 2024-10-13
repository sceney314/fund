package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class GuPiaoWorker extends Base {
    /**
     * worker类型
     */
    private Integer workerType;

    /**
     * worker 状态
     */
    private Integer workerStatus;

    /**
     * 信号日期
     */
    private LocalDate signalDate;
}