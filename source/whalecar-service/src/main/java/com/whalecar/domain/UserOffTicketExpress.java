package com.whalecar.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruihuang on 14-5-7.
 */

public class UserOffTicketExpress implements Serializable {
    private Integer id;
    private Date createDate;
    private String ticketSn;
    private String phoneNum;
    private Integer car_ModelLv3;
    private String username;
    private String title;

    public Integer getCar_ModelLv3() {
        return car_ModelLv3;
    }

    public void setCar_ModelLv3(Integer car_ModelLv3) {
        this.car_ModelLv3 = car_ModelLv3;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
