package com.fund.www.provider.repository.external.impl;

import com.alibaba.fastjson.JSON;
import com.fund.www.provider.bean.dto.sina.BasicGuPiaoDTO;
import com.fund.www.provider.bean.po.BaseGuPiao;
import com.fund.www.provider.bean.po.GuPiaoWorker;
import com.fund.www.provider.common.WorkerStatusEnum;
import com.fund.www.provider.common.WorkerTypeEnum;
import com.fund.www.provider.common.constants.FundConst;
import com.fund.www.provider.common.constants.TxlConst;
import com.fund.www.provider.common.futu.MarketEnum;
import com.fund.www.provider.dao.BaseGuPiaoDao;
import com.fund.www.provider.dao.GuPiaoWorkerDao;
import com.fund.www.provider.repository.external.SinaBasicGuPiaoRepository;
import com.fund.www.provider.utils.GuavaCacheUtils;
import com.fund.www.provider.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 新浪拉取股票基本信息
 */
@Slf4j
@Service
public class SinaBasicGuPiaoRepositoryImpl implements SinaBasicGuPiaoRepository {
    /**
     * 股票URL
     */
    private static final String URL_SINA_STOCK = "https://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page={page}&num={pageSize}&asc=1&node={market}";

    @Resource
    private BaseGuPiaoDao baseGuPiaoDao;

    @Resource
    private GuPiaoWorkerDao  guPiaoWorkerDao;

    @Override
    public void processBasicGuPiaoInfo() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TxlConst.DATE_PATTERN_YYYY_MM_DD));
        String key = "SINA_BASIC_GUPIAO_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(TxlConst.DATE_PATTERN_YYYY_MM_DD));
        String val = GuavaCacheUtils.getSwitchCache(key);
        if (Objects.equals(FundConst.YES_STRING, val)){
            System.out.println("日期[" + date + "][股票检查]，今日已执行，不再执行");
            return;
        }

        GuPiaoWorker worker = guPiaoWorkerDao.queryByWorkerType(WorkerTypeEnum.TYPE_GU_PIAO_IMPORT.getCode(), LocalDate.now());
        if (Objects.nonNull(worker)){
            GuavaCacheUtils.putSwitchCache(key, FundConst.YES_STRING);
            System.out.println("日期[" + date + "][股票检查]，今日已执行，不再执行");
            return;
        }

        Set<String> codeSet = new HashSet<>();

        int pageSize = 100;
        for (MarketEnum market : MarketEnum.values()){
            int idx = 0;
            while (idx++ < 1000){
                // 防止被禁止 IP
                try {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    long sleepTime = random.nextLong(30L, 70L);
                    TimeUnit.SECONDS.sleep(sleepTime);
                    String url = URL_SINA_STOCK.replace("{page}", idx + "").replace("{market}", market.getSinaMarket()).replace("{pageSize}", pageSize + "");
                    String result = HttpUtil.getRequest(url);
                    List<BasicGuPiaoDTO> dtoList = JSON.parseArray(result, BasicGuPiaoDTO.class);
                    if (CollectionUtils.isEmpty(dtoList)){
                        break;
                    }
                    Set<String> item = dtoList.stream().map(BasicGuPiaoDTO::getCode).collect(Collectors.toSet());
                    codeSet.addAll(item);
                    addBasicInfo(dtoList, market);
                }catch (Exception e){
                    e.printStackTrace();
                    try {
                        TimeUnit.SECONDS.sleep(600L);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }

        GuPiaoWorker piaoWorker = new GuPiaoWorker();
        piaoWorker.setWorkerType(WorkerTypeEnum.TYPE_GU_PIAO_IMPORT.getCode());
        piaoWorker.setSignalDate(LocalDate.now());
        piaoWorker.setWorkerStatus(WorkerStatusEnum.SUCCESS.getCode());
        guPiaoWorkerDao.insertWorker(piaoWorker);
        guPiaoWorkerDao.finishWorkerSuccess(piaoWorker.getId());

        GuavaCacheUtils.putSwitchCache(key, FundConst.YES_STRING);

        // 清除 code
        checkGuPiao(codeSet);
    }

    /**
     * 检查股票
     *
     * @param codeSet 股票编码集合
     */
    private void checkGuPiao(Set<String> codeSet){
        if (Objects.isNull(codeSet) || codeSet.isEmpty()){
            return;
        }
        long start = 0L;
        while (true){
            List<BaseGuPiao> piaoList = baseGuPiaoDao.queryByPageList(start);
            if (CollectionUtils.isEmpty(piaoList)){
                break;
            }
            List<String> codeList = piaoList.stream()
                    .map(BaseGuPiao::getGupiaoCode)
                    .filter(code -> !codeSet.contains(code))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(codeList)){
                baseGuPiaoDao.deleteByCodeList(codeList);
            }
            Optional<Long> max = piaoList.stream().map(BaseGuPiao::getId).max(Long::compareTo);
            if (max.isPresent()){
                start = max.get();
            }else{
                start += 500L;
            }
        }
    }

    /**
     * 添加股票基础信息
     *
     * @param dtoList 股票列表
     * @param market 市场
     */
    private void addBasicInfo(List<BasicGuPiaoDTO> dtoList, MarketEnum market){
        if (CollectionUtils.isEmpty(dtoList) || Objects.isNull(market)){
            return;
        }
        List<BaseGuPiao> guPiaoList = baseGuPiaoDao.queryByCodeList(dtoList.stream().map(BasicGuPiaoDTO::getCode).collect(Collectors.toList()));
        if (Objects.isNull(guPiaoList)){
            guPiaoList = new ArrayList<>();
        }
        Set<String> codeSet = guPiaoList.stream().map(BaseGuPiao::getGupiaoCode).collect(Collectors.toSet());
        List<BaseGuPiao> piaoList = dtoList.stream()
                .filter(dto -> !codeSet.contains(dto.getCode()))
                .map(dto -> {
                    BaseGuPiao piao = new BaseGuPiao();
                    piao.setMarket(market.getCode());
                    piao.setGupiaoCode(dto.getCode());
                    piao.setGupiaoName(dto.getName());
                    piao.setSymbol(dto.getSymbol());
                    return piao;
                })
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(piaoList)){
            return;
        }
        baseGuPiaoDao.batchInsertBaseGuPiao(piaoList);
    }
}
