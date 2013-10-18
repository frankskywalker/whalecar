package com.whalecar.domain;

import com.whalecar.persistence.enums.UserSubmitPriceProcessStateEnum;
import com.whalecar.persistence.enums.UserSubmitPriceStateEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ruihuang on 13-9-1.
 */
public class UserSubmitPrice implements Serializable {
    private Integer id;

    private String state;
    private String stateText;
    private Date stateChangeDate;
    private Date createDate;
    private Date replyDate;
    private BigDecimal originalPrice;
    private int shopStock;
    private int shop;
    private int user;
    private BigDecimal userPrice;
    private BigDecimal shopPrice;

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

    public Date getStateChangeDate() {
        return stateChangeDate;
    }

    public void setStateChangeDate(Date stateChangeDate) {
        this.stateChangeDate = stateChangeDate;
    }

    public String getStateText() {
        return UserSubmitPriceStateEnum.valueOf(this.state).getStateCName();
    }

    public BigDecimal getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(BigDecimal userPrice) {
        this.userPrice = userPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }
}
