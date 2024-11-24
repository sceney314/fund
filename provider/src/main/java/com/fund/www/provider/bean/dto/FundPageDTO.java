package com.fund.www.provider.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class FundPageDTO implements Serializable {
    /**
     * 基金数据
     */
    private List<FundDTO> data;

    /**
     * 总数量
     */
    @JSONField(name = "totalnum")
    private int totalNum;

    /**
     * 最大延迟
     */
    @JSONField(name = "maxdaily")
    private String maxDaily;
}
