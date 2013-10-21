package com.whalecar.domain;

/**
 * Created by ruihuang on 13-10-21.
 */
public class UserOrderView extends UserOrder {
    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户电话
     */
    private String userTel;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
}
