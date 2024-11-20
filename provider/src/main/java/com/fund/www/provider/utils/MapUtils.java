package com.fund.www.provider.utils;

import java.util.Map;
import java.util.Objects;

/**
 * MAP 工具类
 */
public class MapUtils {
    public static <K, V> boolean isEmpty(Map<K, V> map){
        return Objects.isNull(map) || map.isEmpty();
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map){
        return !isEmpty(map);
    }
}
