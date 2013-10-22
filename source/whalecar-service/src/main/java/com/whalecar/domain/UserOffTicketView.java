package com.whalecar.domain;

/**
 * Created by ruihuang on 13-9-10.
 */
public class UserOffTicketView extends UserOffTicket{
    private ShopStockView shopStockView;
    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户电话
     */
    private String userTel;

    public ShopStockView getShopStockView() {
        return shopStockView;
    }

    public void setShopStockView(ShopStockView shopStockView) {
        this.shopStockView = shopStockView;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
