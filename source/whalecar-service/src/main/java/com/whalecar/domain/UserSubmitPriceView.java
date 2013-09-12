package com.whalecar.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ruihuang on 13-9-10.
 */
public class UserSubmitPriceView extends UserSubmitPrice {
    private ShopStockView shopStockView;

    private String stateText;

    public ShopStockView getShopStockView() {
        return shopStockView;
    }

    public void setShopStockView(ShopStockView shopStockView) {
        this.shopStockView = shopStockView;
    }

    public String getStateText() {
        if(StringUtils.equals("waiting_reply",this.getState())){
            return "未回复";
        }
        else if(StringUtils.equals("finish_reply",this.getState())){
            return "完成回复";
        }
        return "";
    }
}
