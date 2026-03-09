package com.fund.www.provider.bean.vo;

import com.fund.www.provider.bean.po.PlateGuPiao;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PlateNode implements Serializable {
    /**
     * 学号
     */
    private int serialNo;

    /**
     * 板块列表
     */
    private List<PlateGuPiao> plateList;

    public PlateNode() {
    }

    public PlateNode(int serialNo) {
        this.serialNo = serialNo;
    }

    public PlateNode(int serialNo, List<PlateGuPiao> plateList) {
        this.serialNo = serialNo;
        this.plateList = plateList;
    }
}
