package com.whalecar.domain;

import java.math.BigDecimal;

/**
 * Created by wufei on 14/11/24.
 */
public class WxWebCarPrice {
    public BigDecimal getWebPrice() {
        return webPrice;
    }

    public void setWebPrice(BigDecimal webPrice) {
        this.webPrice = webPrice;
    }

    private BigDecimal webPrice;


}
