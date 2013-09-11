package com.whalecar.domain;

/**
 * Created by ruihuang on 13-9-10.
 */
public class UserOffTicketView extends UserOffTicket{
    private ShopStockView shopStockView;

    public ShopStockView getShopStockView() {
        return shopStockView;
    }

    public void setShopStockView(ShopStockView shopStockView) {
        this.shopStockView = shopStockView;
    }
}
