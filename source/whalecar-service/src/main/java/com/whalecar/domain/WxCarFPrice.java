package com.whalecar.domain;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wufei on 14/11/24.
 */
public class WxCarFPrice {
    public Integer getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(Integer factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public Integer getLv3Id() {
        return Lv3Id;
    }

    public void setLv3Id(Integer lv3Id) {
        Lv3Id = lv3Id;
    }

    public List<WxWebCarPrice> getWxWebCarPriceList() {
        return wxWebCarPriceList;
    }

    public void setWxWebCarPriceList(List<WxWebCarPrice> wxWebCarPriceList) {
        this.wxWebCarPriceList = wxWebCarPriceList;
    }

    private List<WxWebCarPrice> wxWebCarPriceList;
    private Integer Lv3Id;
    private Integer factoryPrice;
}
