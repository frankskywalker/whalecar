package com.whalecar.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ruihuang on 13-9-10.
 */
public class UserSubmitPriceView extends UserSubmitPrice {
    private ShopStockView shopStockView;

    public ShopStockView getShopStockView() {
        return shopStockView;
    }

    public void setShopStockView(ShopStockView shopStockView) {
        this.shopStockView = shopStockView;
    }

}
