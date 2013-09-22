package com.whalecar.persistence.enums;

/**
 * 汽车订单处理状态Enum
 *
 * Created by ruihuang on 13-9-17.
 */
public enum UserOrderProcessStateEnum {

    wait_contact("未跟进")
    ,has_contact("已跟进")
    ,order_submit("已订车")
    ,picked("已取车");

    private UserOrderProcessStateEnum(String stateCName){
        this.stateCName = stateCName;
    }

    private String stateCName;

    public String getStateCName() {
        return stateCName;
    }
}
