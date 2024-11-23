package com.fund.www.provider.bean.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class SinaFundResultDTO implements Serializable {
    /**
     * 结果
     */
    private SinaFundResponse result;

    public <T> T getDataObject(Class<T> clazz){
        return JSON.parseObject(result.getData(), clazz);
    }

    public <T> List<T> getDataList(Class<T> clazz){
        return JSON.parseArray(result.getData(), clazz);
    }

    public <T> T getDataMap(TypeReference<T> reference){
        return JSON.parseObject(result.getData(), reference);
    }

    @Setter
    @Getter
    @ToString
    private static class SinaFundResponse implements Serializable{
        /**
         * 状态
         */
        private SinaFundStatus status;

        /**
         * 数据
         */
        private String data;
    }

    @Setter
    @Getter
    @ToString
    private static class SinaFundStatus implements Serializable{
        /**
         * 成功状态码
         */
        private static final int SUCCESS = 0;

        /**
         * 状态码
         */
        private int code;
    }
}
