package com.fund.www.provider.bean.model;

import com.fund.www.provider.bean.po.GuPiao;
import com.fund.www.provider.bean.po.GuPiaoImportResult;
import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.common.WorkerStatusEnum;
import com.fund.www.provider.common.WorkerTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
public class ImportModel implements Serializable {
    /**
     * 是否强制分析
     */
    private boolean forceAnalyze;

    /**
     * 信号日期
     */
    private LocalDate signalDate;

    /**
     * 策略类型
     */
    private Integer ployType;

    /**
     * 证券名称
     */
    private String ployName;

    /**
     * 股票列表
     */
    private List<GuPiao> piaoList;

    public ImportModel() {
    }

    public ImportModel(LocalDate signalDate, Integer ployType, String ployName) {
        this.signalDate = signalDate;
        this.ployType = ployType;
        this.ployName = ployName;
    }

    /**
     * 构建导入结果
     *
     * @return GuPiaoImportResult
     */
    public GuPiaoImportResult buildImportResult(){
        GuPiaoImportResult result = new GuPiaoImportResult();
        result.setPloyType(ployType);
        result.setPloyName(ployName);
        result.setSignalDate(signalDate);
        result.setImportStatus(1);
        return result;
    }

    /**
     * 构建 worker 列表
     *
     * @return List
     */
    public List<GuPiaoWorker> buildAnalyzeWorkerList(){
        return WorkerTypeEnum.getAnalyzeALLInstance().stream()
                .map(type -> {
                    GuPiaoWorker worker = new GuPiaoWorker();
                    worker.setWorkerType(type.getCode());
                    worker.setSignalDate(signalDate);
                    worker.setWorkerStatus(WorkerStatusEnum.INIT.getCode());
                    return worker;
                })
                .collect(Collectors.toList());
    }
}
