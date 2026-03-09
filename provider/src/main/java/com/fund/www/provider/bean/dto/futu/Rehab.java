package com.fund.www.provider.bean.dto.futu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 复权信息
 */
@Setter
@Getter
@ToString
public class Rehab {
    private Integer time = 1; //时间字符串（格式：yyyy-MM-dd）
    private Integer companyActFlag = 2; //公司行动(CompanyAct)组合标志位,指定某些字段值是否有效
    private Integer fwdFactorA = 3; //前复权因子 A
    private Integer fwdFactorB = 4; //前复权因子 B
    private Integer bwdFactorA = 5; //后复权因子 A
    private Integer bwdFactorB = 6; //后复权因子 B
    private Integer splitBase = 7; //拆股(例如，1拆5，Base 为1，Ert 为5)
    private Integer splitErt = 8;
    private Integer joinBase = 9; //合股(例如，50合1，Base 为50，Ert 为1)
    private Integer joinErt = 10;
    private Integer bonusBase = 11; //送股(例如，10送3, Base 为10,Ert 为3)
    private Integer bonusErt = 12;
    private Integer transferBase = 13; //转赠股(例如，10转3, Base 为10,Ert 为3)
    private Integer transferErt = 14;
    private Integer allotBase = 15; //配股(例如，10送2, 配股价为6.3元, Base 为10, Ert 为2, Price 为6.3)
    private Integer allotErt = 16;
    private Integer allotPrice = 17;
    private Integer addBase = 18; //增发股(例如，10送2, 增发股价为6.3元, Base 为10, Ert 为2, Price 为6.3)
    private Integer addErt = 19;
    private Integer addPrice = 20;
    private Integer dividend = 21; //现金分红(例如，每10股派现0.5元,则该字段值为0.05)
    private Integer spDividend = 22; //特别股息(例如，每10股派特别股息0.5元,则该字段值为0.05)
    private Integer timestamp = 23; //时间戳
}
