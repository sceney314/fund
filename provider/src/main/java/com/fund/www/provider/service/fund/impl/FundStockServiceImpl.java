package com.fund.www.provider.service.fund.impl;

import com.fund.www.provider.bean.po.Stock;
import com.fund.www.provider.dao.StockDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.service.fund.FundStockService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class FundStockServiceImpl implements FundStockService {
    @Resource
    private StockDao stockDao;

    @Override
    public List<Stock> queryAllStock() {
        List<Stock> result = new ArrayList<>();
        Long startId = 0L;
        int pageSize = 200;
        while (true){
            List<Stock> list = stockDao.queryStockFlow(startId,  pageSize);
            if (CollectionUtils.isEmpty(list)){
                break;
            }
            result.addAll(list);
            startId = list.stream().map(Stock::getId).max(Long::compareTo).orElseThrow(() -> new ServiceException("查询最大 ID"));
        }
        return result;
    }
}
