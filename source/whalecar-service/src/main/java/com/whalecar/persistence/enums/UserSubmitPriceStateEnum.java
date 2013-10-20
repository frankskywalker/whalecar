package com.whalecar.persistence.enums;

/**
 * 用户提交价格处理状态Enum
 *
 * Created by ruihuang on 13-9-17.
 */
public enum UserSubmitPriceStateEnum {

    user_commit("用户提交")
    ,shop_agree("价格确认")
    ,shop_commit("4s店议价")
    ,price_success("议价成功")
    ,price_fail("议价失败")
    ,create_car_order("已生成订单");

    private UserSubmitPriceStateEnum(String stateCName){
        this.stateCName = stateCName;
    }

    private String stateCName;

    public String getStateCName() {
        return stateCName;
    }
}
