package com.fund.www.provider.service.gupiao;

import com.fund.www.provider.bean.model.ImportModel;
import com.fund.www.provider.bean.po.GuPiao;
import com.fund.www.provider.bean.po.GuPiaoAnalyzeResult;

import java.time.LocalDate;
import java.util.List;

public interface GuPiaoService {
    /**
     * 股票列表
     *
     * @param model 导入model
     */
    void triggerSignal(ImportModel model);

    /**
     * 展示分析结果
     *
     * @return List
     */
    List<GuPiaoAnalyzeResult> showAnalyze();

    /**
     * 强制分析结果
     */
    void forceAnalyze(LocalDate signalDate);

}
