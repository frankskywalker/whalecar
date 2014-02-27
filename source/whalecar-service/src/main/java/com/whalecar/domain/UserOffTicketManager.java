package com.whalecar.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ruihuang on 14-2-26.
 */
public class UserOffTicketManager implements Serializable{
    private Date createDate;
    private String ticketSn;
    private String phoneNum;
    private String state;
    private String shopName;
    private String shopTel;
    private String fullName;
    private BigDecimal factoryPrice;
    private String cnameLv1;
    private String cnameCb;
    private String userName;

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

    private String userEmail;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(BigDecimal factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public String getCnameLv1() {
        return cnameLv1;
    }

    public void setCnameLv1(String cnameLv1) {
        this.cnameLv1 = cnameLv1;
    }

    public String getCnameCb() {
        return cnameCb;
    }

    public void setCnameCb(String cnameCb) {
        this.cnameCb = cnameCb;
    }
}
