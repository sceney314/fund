package com.fund.www.provider.common.futu;

public enum FuTuSubjectEnum {
    PLATE("plate", "股票板块"),
    ;
    private final String subject;
    private final String msg;

    FuTuSubjectEnum(String subject, String msg) {
        this.subject = subject;
        this.msg = msg;
    }

    public String getSubject() {
        return subject;
    }

    public String getMsg() {
        return msg;
    }
}
