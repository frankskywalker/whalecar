package com.whalecar.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wufei on 14/11/23.
 */
public class WxCarModel {
    private String cname;
    private Integer LV1Id;
    private List<WxLV2> wxLV2List;


    public BigDecimal getWxCarPrice() {
        return wxCarPrice;
    }

    public void setWxCarPrice(BigDecimal wxCarPrice) {
        this.wxCarPrice = wxCarPrice;
    }

    private BigDecimal wxCarPrice;

    public List<WxLV2> getWxLV2List() {
        return wxLV2List;
    }

    public void setWxLV2List(List<WxLV2> wxLV2List) {
        this.wxLV2List = wxLV2List;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getLV1Id() {
        return LV1Id;
    }

    public void setLV1Id(Integer LV1Id) {
        this.LV1Id = LV1Id;
    }


}
