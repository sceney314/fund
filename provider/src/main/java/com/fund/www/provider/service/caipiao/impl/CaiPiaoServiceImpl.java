package com.fund.www.provider.service.caipiao.impl;

import com.fund.www.provider.bean.dto.ShuangSeQiuDTO;
import com.fund.www.provider.bean.po.CaiPiao;
import com.fund.www.provider.common.CaiPiaoTypeEnum;
import com.fund.www.provider.dao.CaiPiaoDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.service.caipiao.CaiPiaoService;
import com.fund.www.provider.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CaiPiaoServiceImpl implements CaiPiaoService {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private CaiPiaoDao caiPiaoDao;

    /**
     * 双色球地址
     */
    private static final String URL_SHUANG_SE_QIU = "https://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart=&issueEnd=&dayStart=&dayEnd=&pageNo={page}&pageSize=30&week=&systemType=PC";

    @Override
    public void triggerShuangSeQiuInit() {
        threadPoolTaskExecutor.submit(() -> {
            int max = 0;
            while (max++ < 1000){
                try {
                    Thread.sleep(1000L);
                    System.out.println("爬取第[" + max + "]页数据 -------> 开始");
                    String url = URL_SHUANG_SE_QIU.replace("{page}", max + "");
                    ShuangSeQiuDTO dto = HttpUtil.getObjectSimpleRequest(url, ShuangSeQiuDTO.class);
                    insertShuangSeQiu(dto);
                    System.out.println("爬取第[" + max + "]页数据 <------- 结束");
                    System.out.println("");
                    if (dto.getPageNo().compareTo(dto.getPageNum()) >= 0){
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 添加双色球
     *
     * @param dto 结果
     */
    private void insertShuangSeQiu(ShuangSeQiuDTO dto){
        if (Objects.isNull(dto)){
            return;
        }
        if (CollectionUtils.isEmpty(dto.getResult()) && dto.getPageNo().compareTo(dto.getPageNum()) < 0){
            return;
        }
        List<String> codeList = dto.getResult().stream().map(ShuangSeQiuDTO.ShuangSeQiuDetail::getCaiPiaoCode).distinct().collect(Collectors.toList());
        List<CaiPiao> existList = caiPiaoDao.getByCaiPiaoCode(CaiPiaoTypeEnum.SHUANG_SE_QIU.getCode(), codeList);
        final Set<String> codeSet = CollectionUtils.isEmpty(existList) ? new HashSet<>() : existList.stream().map(CaiPiao::getCaiPiaoCode).collect(Collectors.toSet());
        List<CaiPiao> piaoList = dto.getResult().stream()
                .map(d -> CaiPiao.getInstance(CaiPiaoTypeEnum.SHUANG_SE_QIU, d))
                .filter(p -> !codeSet.contains(p.getCaiPiaoCode()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(piaoList)){
            return;
        }
        int row = caiPiaoDao.batchInsertCaiPiao(piaoList);
        if (row != piaoList.size()){
            throw new ServiceException("插入数据异常");
        }
    }

    @Override
    public void triggerShuangSeQiu() {
        String url = URL_SHUANG_SE_QIU.replace("{page}", "1");
        ShuangSeQiuDTO dto = HttpUtil.getObjectSimpleRequest(url, ShuangSeQiuDTO.class);
        insertShuangSeQiu(dto);
    }
}
