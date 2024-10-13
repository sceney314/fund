package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class GuPiaoImportResult extends Base{
    /**
     * 策略类型
     */
    private Integer ployType;

    /**
     * 策略名字
     */
    private String ployName;

    /**
     * 导入状态
     */
    private Integer importStatus;

    /**
     * 信号日期
     */
    private LocalDate signalDate;
}