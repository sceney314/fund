package com.fund.www.provider.service.business;

import com.fund.www.provider.bean.po.FundType;
import com.fund.www.provider.dao.FundTypeDao;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundTypeService {
    @Autowired
    private FundTypeDao fundTypeDao;

    public void addFundType(String code, String name){
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(name)){
            throw new ServiceException("参数异常");
        }

        if (1 > fundTypeDao.addFundType(FundType.makeType(code, name))){
            throw new ServiceException("添加基金类型失败");
        }
    }
}
