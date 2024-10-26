package com.fund.www.provider.bean.po;

import com.fund.www.provider.bean.dto.ShuangSeQiuDTO;
import com.fund.www.provider.common.CaiPiaoTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
public class CaiPiao extends Base{
    /**
     * 彩票名字
     */
    private String caiPiaoName;

    /**
     * 彩票类型
     */
    private Integer caiPiaoType;

    /**
     * 彩票期号
     */
    private String caiPiaoCode;

    /**
     * 开奖时间
     */
    private String openDate;

    /**
     * 蓝色球
     */
    private Integer blue;

    /**
     * 红球 1
     */
    private Integer redOne;

    /**
     * 红球 2
     */
    private Integer redTwo;

    /**
     * 红球 3
     */
    private Integer redThree;

    /**
     * 红球 4
     */
    private Integer redFour;

    /**
     * 红球 5
     */
    private Integer redFive;

    /**
     * 红球 6
     */
    private Integer redSix;

    /**
     * 根据解析结果和类型生产实体对象
     *
     * @param type 类型
     * @param detail 明细
     * @return CaiPiao
     */
    public static CaiPiao getInstance(CaiPiaoTypeEnum type, ShuangSeQiuDTO.ShuangSeQiuDetail detail){
        List<Integer> redList = Arrays.stream(detail.getRed().split(",")).map(Integer::parseInt).sorted(Integer::compareTo).collect(Collectors.toList());
        CaiPiao piao = new CaiPiao();
        piao.setCaiPiaoName(type.getName());
        piao.setCaiPiaoType(type.getCode());
        piao.setCaiPiaoCode(detail.getCaiPiaoCode());
        piao.setOpenDate(detail.getDate());
        piao.setBlue(Integer.parseInt(detail.getBlue()));
        piao.setRedOne(redList.get(0));
        piao.setRedTwo(redList.get(1));
        piao.setRedThree(redList.get(2));
        piao.setRedFour(redList.get(3));
        piao.setRedFive(redList.get(4));
        piao.setRedSix(redList.get(5));
        return piao;
    }

}