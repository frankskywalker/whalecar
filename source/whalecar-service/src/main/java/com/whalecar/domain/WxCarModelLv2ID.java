package com.whalecar.domain;

import java.util.List;

/**
 * Created by wufei on 14/11/24.
 */
public class WxCarModelLv2ID {
    public Integer getLV2Id() {
        return LV2Id;
    }

    public void setLV2Id(Integer LV2Id) {
        this.LV2Id = LV2Id;
    }

    public List<WxCarFPrice> getWxCarFPriceList() {
        return wxCarFPriceList;
    }

    public void setWxCarFPriceList(List<WxCarFPrice> wxCarFPriceList) {
        this.wxCarFPriceList = wxCarFPriceList;
    }

    private List<WxCarFPrice> wxCarFPriceList;
    private Integer LV2Id;
}
