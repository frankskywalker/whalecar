package com.whalecar.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ruihuang on 13-9-1.
 */
public class UserSubmitPrice implements Serializable {
    private Integer id;
    /**
     *订单状态（未回复:waiting_reply、完成回复:finish_reply）
     */
    private String state;
    private Date createDate;
    private Date replyDate;
    private BigDecimal originalPrice;
    private BigDecimal submitPrice;
    private int shopStock;
    private int shop;
    private int user;

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSubmitPrice() {
        return submitPrice;
    }

    public void setSubmitPrice(BigDecimal submitPrice) {
        this.submitPrice = submitPrice;
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
}
