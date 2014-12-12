package com.whalecar.domain;

import java.math.BigDecimal;

/**
 * Created by wufei on 14/11/23.
 */
public class WxCarPrice {


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;
}
