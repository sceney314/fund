package com.fund.www.provider.service.gupiao;

import com.fund.www.provider.bean.model.ImportModel;
import com.fund.www.provider.bean.po.GuPiao;

import java.util.List;

public interface GuPiaoService {
    /**
     * 股票列表
     *
     * @param model 导入model
     */
    void triggerSignal(ImportModel model);
}
