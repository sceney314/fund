package com.fund.www.provider.common.futu;

/**
 * 逐笔成交类型
 */
public enum TickerTypeEnum {
    TICKER_TYPE_UNKNOWN(0,"未知"),
    TICKER_TYPE_AUTOMATCH(1,"自动对盘"),
    TICKER_TYPE_LATE(2,"开市前成交盘"),
    TICKER_TYPE_NONEAUTOMATCH(3,"非自动对盘"),
    TICKER_TYPE_INTERAUTOMATCH(4,"同一证券商自动对盘"),
    TICKER_TYPE_INTERNONEAUTOMATCH(5,"同一证券商非自动对盘"),
    TICKER_TYPE_ODDLOT(6,"碎股交易"),
    TICKER_TYPE_AUCTION(7,"竞价交易"),
    TICKER_TYPE_BULK(8,"批量交易"),
    TICKER_TYPE_CRASH(9,"现金交易"),
    TICKER_TYPE_CROSSMARKET(10,"跨市场交易"),
    TICKER_TYPE_BULKSOLD(11,"批量卖出"),
    TICKER_TYPE_FREEONBOARD(12,"离价交易"),
    TICKER_TYPE_RULE127OR155(13,"第127条交易（纽交所规则）或第155条交易"),
    TICKER_TYPE_DELAY(14,"延迟交易"),
    TICKER_TYPE_MARKETCENTERCLOSEPRICE(15,"中央收市价"),
    TICKER_TYPE_NEXTDAY(16,"隔日交易"),
    TICKER_TYPE_MARKETCENTEROPENING(17,"中央开盘价交易"),
    TICKER_TYPE_PRIORREFERENCEPRICE(18,"前参考价"),
    TICKER_TYPE_MARKETCENTEROPENPRICE(19,"中央开盘价"),
    TICKER_TYPE_SELLER(20,"卖方"),
    TICKER_TYPE_T(21,"T 类交易(盘前和盘后交易)"),
    TICKER_TYPE_EXTENDEDTRADINGHOURS(22,"延长交易时段"),
    TICKER_TYPE_CONTINGENT(23,"合单交易"),
    TICKER_TYPE_AVGPRICE(24,"平均价成交"),
    TICKER_TYPE_OTCSOLD(25,"场外售出"),
    TICKER_TYPE_ODDLOTCROSSMARKET(26,"碎股跨市场交易"),
    TICKER_TYPE_DERIVATIVELYPRICED(27,"衍生工具定价"),
    TICKER_TYPE_REOPENINGPRICED(28,"再开盘定价"),
    TICKER_TYPE_CLOSINGPRICED(29,"收盘定价"),
    TICKER_TYPE_COMPREHENSIVEDELAYPRICE(30,"综合延迟价格"),
    TICKER_TYPE_OVERSEAS(31,"交易的一方不是香港交易所的成员，属于场外交易"),
    ;
    private final int code;
    private final String msg;

    TickerTypeEnum(int code, String msg) {
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
