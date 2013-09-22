package com.whalecar.persistence.enums;

/**
 * Created by ruihuang on 13-9-19.
 */
public enum UserOrderStateEnum {
    waiting_pay("等待付款"),
    waiting_confirm("等待确认"),
    order_succ("预定成功"),
    finish("已完成"),
    cancel("已取消");

    private UserOrderStateEnum(String cName){
        this.cName = cName;
    }

    private String cName;

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }
}
