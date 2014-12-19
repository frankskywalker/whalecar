package com.whalecar.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wufei on 14/11/24.
 */
public class WxCarDetailPrice {
    private BigDecimal factoryPriceMax;
    private BigDecimal factoryPriceMin;
    private BigDecimal webPriceMax;
    private BigDecimal webPriceMin;
    private List<WxCarModelLv2ID> wxCarModelLv2IDList;

    public BigDecimal getWebPriceMax() {
        return webPriceMax;
    }

    public void setWebPriceMax(BigDecimal webPriceMax) {
        this.webPriceMax = webPriceMax;
    }

    public BigDecimal getFactoryPriceMax() {
        return factoryPriceMax;
    }

    public void setFactoryPriceMax(BigDecimal factoryPriceMax) {
        this.factoryPriceMax = factoryPriceMax;
    }

    public BigDecimal getFactoryPriceMin() {
        return factoryPriceMin;
    }

    public void setFactoryPriceMin(BigDecimal factoryPriceMin) {
        this.factoryPriceMin = factoryPriceMin;
    }

    public BigDecimal getWebPriceMin() {
        return webPriceMin;
    }

    public void setWebPriceMin(BigDecimal webPriceMin) {
        this.webPriceMin = webPriceMin;
    }

    public List<WxCarModelLv2ID> getWxCarModelLv2IDList() {
        return wxCarModelLv2IDList;
    }

    public void setWxCarModelLv2IDList(List<WxCarModelLv2ID> wxCarModelLv2IDList) {
        this.wxCarModelLv2IDList = wxCarModelLv2IDList;
    }



}
