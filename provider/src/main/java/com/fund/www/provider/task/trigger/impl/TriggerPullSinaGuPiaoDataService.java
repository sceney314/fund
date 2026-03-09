package com.fund.www.provider.task.trigger.impl;

import com.fund.www.provider.repository.external.SinaBasicGuPiaoRepository;
import com.fund.www.provider.task.trigger.WorkerTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 拉取新浪股票信息
 */
@Slf4j
@Component
public class TriggerPullSinaGuPiaoDataService implements WorkerTriggerService {
    @Resource
    private SinaBasicGuPiaoRepository sinaBasicGuPiaoRepository;

    @Override
    public void trigger() {
        sinaBasicGuPiaoRepository.processBasicGuPiaoInfo();
    }
}
