package com.whalecar.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wufei on 14/11/23.
 */
public class WxLv3 {
    public Integer getCarLV3ID() {
        return carLV3ID;
    }

    public void setCarLV3ID(Integer carLV3ID) {
        this.carLV3ID = carLV3ID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;
    private  Integer carLV3ID;
}
