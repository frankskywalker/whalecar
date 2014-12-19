package com.whalecar.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wufei on 14/11/24.
 */
public class WxCarDetail {
    private BigDecimal factoryPrice;
    private BigDecimal webPrice;
    private List<WxLV2> wxLV2List;

    public BigDecimal getWebPrice() {
        return webPrice;
    }

    public void setWebPrice(BigDecimal webPrice) {
        this.webPrice = webPrice;
    }

    public BigDecimal getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(BigDecimal factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public List<WxLV2> getWxLV2List() {
        return wxLV2List;
    }

    public void setWxLV2List(List<WxLV2> wxLV2List) {
        this.wxLV2List = wxLV2List;
    }


}
