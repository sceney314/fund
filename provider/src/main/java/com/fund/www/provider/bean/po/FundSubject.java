package com.fund.www.provider.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FundSubject extends Base{
    /**
     * 主题编码
     */
    private String subjectCode;

    /**
     * 主题名字
     */
    private String subjectName;

    public FundSubject() {
    }

    public FundSubject(String subjectCode, String subjectName) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }
}
