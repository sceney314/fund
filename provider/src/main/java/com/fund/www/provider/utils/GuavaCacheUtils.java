package com.fund.www.provider.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

public class GuavaCacheUtils {
    /**
     * 开关缓存
     */
    private static final Cache<String, String> switchCache = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .maximumSize(100)
            .build();

    /**
     * 获取开关缓存
     *
     * @param key 开关
     * @return String
     */
    public static String getSwitchCache(String key){
        return switchCache.getIfPresent(key);
    }

    /**
     * 设置开关缓存
     *
     * @param key 开关
     * @param val 值
     */
    public static void putSwitchCache(String key, String val){
        if (StringUtils.isBlank(val)){
            return;
        }
        switchCache.put(key, val);
    }
}
