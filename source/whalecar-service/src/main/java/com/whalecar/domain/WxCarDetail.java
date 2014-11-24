package com.whalecar.domain;

import java.util.List;

/**
 * Created by wufei on 14/11/24.
 */
public class WxCarDetail {
    private Integer factoryPrice;
    private Integer webPrice;
    private List<WxLV2> wxLV2List;

    public Integer getWebPrice() {
        return webPrice;
    }

    public void setWebPrice(Integer webPrice) {
        this.webPrice = webPrice;
    }

    public Integer getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(Integer factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public List<WxLV2> getWxLV2List() {
        return wxLV2List;
    }

    public void setWxLV2List(List<WxLV2> wxLV2List) {
        this.wxLV2List = wxLV2List;
    }


}
