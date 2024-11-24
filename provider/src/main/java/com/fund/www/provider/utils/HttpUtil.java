package com.fund.www.provider.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fund.www.provider.bean.dto.FundTypeItemDTO;
import com.fund.www.provider.bean.dto.SinaFundResultDTO;
import com.fund.www.provider.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;


/**
 * http帮助类
 * <p>
 * Date: 2020/9/2 下午7:17
 * Copyright (C), 2015-2020
 */
@Slf4j
public class HttpUtil {

    private static final int SUCCESS = 200;

    /**
     * 简单请求
     *
     * @param url 请求地址
     * @param clazz class 对象
     * @return R
     */
    public static  <R> R getObjectSimpleRequest(String url, Class<R> clazz){
        try {
            // 创建HttpClient实例
            CookieStore cookieStore = new BasicCookieStore();
            BasicClientCookie cookie = new BasicClientCookie("HMF_CI", "e6c39506096f23a5c8edde35fdf1756490243f6afc2a2ef25ad5748319740f388d2d4cf2b8e345c4dd246e98f56e388ae4d98a807c9a8be3e449d38a5e1c456d0d");
            cookie.setDomain("www.cwl.gov.cn");
            cookie.setPath("/");
            cookieStore.addCookie(cookie);

            HttpClient httpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();
            // 创建HttpGet实例，设置请求的URL
            HttpGet httpGet = new HttpGet(url);
            // 执行请求并获得响应
            HttpResponse response = httpClient.execute(httpGet);
            // 从响应对象中提取字符串
            String result = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(result, clazz);
        }catch (ParseException | IOException e){
//            log.error("URL[" + url + "] GET 请求异常", e);
            throw new ServiceException("GET 请求异常!");
        }
    }

    public static String getRequest(String url){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(url);
            get.setHeader("Content-Type", "text/plain; charset=UTF-8");
            get.setHeader("Accept", "text/plain; charset=UTF-8");

            HttpResponse response = httpClient.execute(get);
            if(SUCCESS != response.getStatusLine().getStatusCode()){
                throw new ServiceException(response.getStatusLine().toString());
            }
            System.out.println("请求状态:" + response.getStatusLine().getStatusCode());
            return EntityUtils.toString(response.getEntity());
        }catch (ParseException | IOException e){
            throw new ServiceException("GET 请求异常!");
        }
    }

    public static void main(String[] args) {
        String url = "https://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getFundTypeList";
        SinaFundResultDTO result = JSON.parseObject(getRequest(url), SinaFundResultDTO.class);
        System.out.println(result.getDataMap(new TypeReference<Map<String, FundTypeItemDTO>>(){}));
    }
}
