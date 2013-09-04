package com.whalecar.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ruihuang on 13-9-1.
 */
public class UserOffTicket implements Serializable {

    private Integer id;
    private Date createDate;
    private String ticketSn;
    private int shopStock;
    private int shop;
    private int user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTicketSn() {
        return ticketSn;
    }

    public void setTicketSn(String ticketSn) {
        this.ticketSn = ticketSn;
    }

    public int getShopStock() {
        return shopStock;
    }

    public void setShopStock(int shopStock) {
        this.shopStock = shopStock;
    }

    public int getShop() {
        return shop;
    }

    public void setShop(int shop) {
        this.shop = shop;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
