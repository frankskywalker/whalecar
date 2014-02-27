package com.whalecar.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ruihuang on 14-2-27.
 */
public class UserSubmitPriceManager implements Serializable {
    private Date createDate;
    private String state;
    private BigDecimal originalPrice;
    private Date stateChangeDate;
    private BigDecimal userPrice;
    private BigDecimal shopPrice;
    private String shopName;
    private String shopTel;
    private String fullName;
    private String cnameLv1;
    private String cnameCb;
    private String userName;
    private String userEmail;

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCnameCb() {
        return cnameCb;
    }

    public void setCnameCb(String cnameCb) {
        this.cnameCb = cnameCb;
    }

    public String getCnameLv1() {
        return cnameLv1;
    }

    public void setCnameLv1(String cnameLv1) {
        this.cnameLv1 = cnameLv1;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public BigDecimal getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(BigDecimal userPrice) {
        this.userPrice = userPrice;
    }

    public Date getStateChangeDate() {
        return stateChangeDate;
    }

    public void setStateChangeDate(Date stateChangeDate) {
        this.stateChangeDate = stateChangeDate;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
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

    private String userTel;
}
