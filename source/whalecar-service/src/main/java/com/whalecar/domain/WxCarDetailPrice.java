package com.whalecar.domain;

import java.util.List;

/**
 * Created by wufei on 14/11/24.
 */
public class WxCarDetailPrice {
    private Integer factoryPriceMax;
    private Integer factoryPriceMin;
    private Integer webPriceMax;
    private Integer webPriceMin;
    private List<WxCarModelLv2ID> wxCarModelLv2IDList;

    public Integer getWebPriceMax() {
        return webPriceMax;
    }

    public void setWebPriceMax(Integer webPriceMax) {
        this.webPriceMax = webPriceMax;
    }

    public Integer getFactoryPriceMax() {
        return factoryPriceMax;
    }

    public void setFactoryPriceMax(Integer factoryPriceMax) {
        this.factoryPriceMax = factoryPriceMax;
    }

    public Integer getFactoryPriceMin() {
        return factoryPriceMin;
    }

    public void setFactoryPriceMin(Integer factoryPriceMin) {
        this.factoryPriceMin = factoryPriceMin;
    }

    public Integer getWebPriceMin() {
        return webPriceMin;
    }

    public void setWebPriceMin(Integer webPriceMin) {
        this.webPriceMin = webPriceMin;
    }

    public List<WxCarModelLv2ID> getWxCarModelLv2IDList() {
        return wxCarModelLv2IDList;
    }

    public void setWxCarModelLv2IDList(List<WxCarModelLv2ID> wxCarModelLv2IDList) {
        this.wxCarModelLv2IDList = wxCarModelLv2IDList;
    }



}
