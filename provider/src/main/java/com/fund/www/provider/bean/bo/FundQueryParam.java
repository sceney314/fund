package com.fund.www.provider.bean.bo;

import com.fund.www.provider.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class FundQueryParam implements Serializable {
    /**
     * 默认每页数量
     */
    private static final int DEFAULT_PAGE_SIZE = 50;

    /**
     * 主题编码
     */
    private String subjectCode;

    /**
     * 基金类型
     */
    private List<String> typeCodeList;

    /**
     * 页数
     */
    private Integer page = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 生成 URL
     *
     * @return  String
     */
    public String generateParam(){
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(subjectCode)){
            sb.append("&st=").append(subjectCode);
        }
        sb.append("&type2=").append(String.join(",", typeCodeList));
        sb.append("&page=").append(page);
        sb.append("&num=").append(pageSize);
        return sb.substring(1);
    }
}
