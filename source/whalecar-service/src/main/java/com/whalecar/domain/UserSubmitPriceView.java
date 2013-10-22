package com.whalecar.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ruihuang on 13-9-10.
 */
public class UserSubmitPriceView extends UserSubmitPrice {
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
