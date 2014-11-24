package com.whalecar.domain;

import java.util.List;

/**
 * Created by wufei on 14/11/23.
 */
public class WxLV2 {
    private Integer carLv2Id;
    private String carType;
    private String colorNum;
    private List<WxLv3> wxLv3List;

    public String getColorRgb() {
        return colorRgb;
    }

    public void setColorRgb(String colorRgb) {
        this.colorRgb = colorRgb;
    }

    private String colorRgb;

    public String getColorNum() {
        return colorNum;
    }

    public void setColorNum(String colorNum) {
        this.colorNum = colorNum;
    }

    public List<WxLv3> getWxLv3List() {
        return wxLv3List;
    }

    public void setWxLv3List(List<WxLv3> wxLv3List) {
        this.wxLv3List = wxLv3List;
    }

    public Integer getCarLv2Id() {
        return carLv2Id;
    }

    public void setCarLv2Id(Integer carLv2Id) {
        this.carLv2Id = carLv2Id;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }


}
