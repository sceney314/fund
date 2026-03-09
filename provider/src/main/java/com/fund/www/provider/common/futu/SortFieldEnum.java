package com.fund.www.provider.common.futu;

/**
 * 排序字段
 */
public enum SortFieldEnum {
    SORT_FIELD_UNKNOW(0,"未知"),
    SORT_FIELD_CODE(1,"代码"),
    SORT_FIELD_CURPRICE(2,"最新价"),
    SORT_FIELD_PRICECHANGEVAL(3,"涨跌额"),
    SORT_FIELD_CHANGERATE(4,"涨跌幅%"),
    SORT_FIELD_STATUS(5,"状态"),
    SORT_FIELD_BIDPRICE(6,"买入价"),
    SORT_FIELD_ASKPRICE(7,"卖出价"),
    SORT_FIELD_BIDVOL(8,"买量"),
    SORT_FIELD_ASKVOL(9,"卖量"),
    SORT_FIELD_VOLUME(10,"成交量"),
    SORT_FIELD_TURNOVER(11,"成交额"),
    SORT_FIELD_AMPLITUDE(30,"振幅%"),
    //以下排序字段只支持用于 Qot_GetWarrant 协议
    SORT_FIELD_SCORE(12,"综合评分"),
    SORT_FIELD_PREMIUM(13,"溢价%"),
    SORT_FIELD_EFFECTIVELEVERAGE(14,"有效杠杆"),
    SORT_FIELD_DELTA(15,"对冲值,仅认购认沽支持该字段"),
    SORT_FIELD_IMPLIEDVOLATILITY(16,"引伸波幅,仅认购认沽支持该字段"),
    SORT_FIELD_TYPE(17,"类型"),
    SORT_FIELD_STRIKEPRICE(18,"行权价"),
    SORT_FIELD_BREAKEVENPOINT(19,"打和点"),
    SORT_FIELD_MATURITYTIME(20,"到期日"),
    SORT_FIELD_LISTTIME(21,"上市日期"),
    SORT_FIELD_LASTTRADETIME(22,"最后交易日"),
    SORT_FIELD_LEVERAGE(23,"杠杆比率"),
    SORT_FIELD_INOUTMONEY(24,"价内/价外%"),
    SORT_FIELD_RECOVERYPRICE(25,"收回价,仅牛熊证支持该字段"),
    SORT_FIELD_CHANGEPRICE(26,"换股价"),
    SORT_FIELD_CHANGE(27,"换股比率"),
    SORT_FIELD_STREETRATE(28,"街货比%"),
    SORT_FIELD_STREETVOL(29,"街货量"),
    SORT_FIELD_WARRANTNAME(31,"窝轮名称"),
    SORT_FIELD_ISSUER(32,"发行人"),
    SORT_FIELD_LOTSIZE(33,"每手"),
    SORT_FIELD_ISSUESIZE(34,"发行量"),
    SORT_FIELD_UPPERSTRIKEPRICE(45,"上限价，仅用于界内证"),
    SORT_FIELD_LOWERSTRIKEPRICE(46,"下限价，仅用于界内证"),
    SORT_FIELD_INLINEPRICESTATUS(47,"界内界外，仅用于界内证"),
    //以下排序字段只支持用于 Qot_GetPlateSecurity 协议，并仅支持美股
    SORT_FIELD_PRECURPRICE(35,"盘前最新价"),
    SORT_FIELD_AFTERCURPRICE(36,"盘后最新价"),
    SORT_FIELD_PREPRICECHANGEVAL(37,"盘前涨跌额"),
    SORT_FIELD_AFTERPRICECHANGEVAL(38,"盘后涨跌额"),
    SORT_FIELD_PRECHANGERATE(39,"盘前涨跌幅%"),
    SORT_FIELD_AFTERCHANGERATE(40,"盘后涨跌幅%"),
    SORT_FIELD_PREAMPLITUDE(41,"盘前振幅%"),
    SORT_FIELD_AFTERAMPLITUDE(42,"盘后振幅%"),
    SORT_FIELD_PRETURNOVER(43,"盘前成交额"),
    SORT_FIELD_AFTERTURNOVER(44,"盘后成交额"),
    //以下排序字段只支持用于 Qot_GetPlateSecurity 协议，并仅支持期货
    SORT_FIELD_LASTSETTLEPRICE(48,"昨结"),
    SORT_FIELD_POSITION(49,"持仓量"),
    SORT_FIELD_POSITIONCHANGE(50,"日增仓"),
    ;
    private final int code;
    private final String msg;

    SortFieldEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
