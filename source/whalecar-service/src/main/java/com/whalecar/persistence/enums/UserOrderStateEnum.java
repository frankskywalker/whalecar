package com.whalecar.persistence.enums;

/**
 * Created by ruihuang on 13-9-19.
 */
public enum UserOrderStateEnum {
    waiting_pay("waiting_pay","等待付款"),
    waiting_confirm("waiting_confirm","等待确认"),
    order_succ("order_succ","预定成功"),
    finish("finish","已完成"),
    cancel("cancel","已取消"),
    out_of_date("out_of_date","已过期"),
    waiting_delivery("waiting_delivery","等待到货");

    private UserOrderStateEnum(String code,String cName){
        this.cName = cName;
        this.code = code;
    }


    private String cName;
    private String code;

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
