package com.whalecar.persistence;

/**
 * Created by ruihuang on 13-8-26.
 */
public interface GenSeralnoMapper {
    public String genUserOrderSN();

    public String genOffTicketByShop(Integer shopId);
}
