package com.whalecar.persistence.enums;

/**
 * Created by ruihuang on 13-9-19.
 */
public enum UserOrderTypeEnum {
    pay_order("pay_order","定金支付订单"),
    not_pay_order("not_pay_order","非定金支付订单"),
    stock_empty_order("stock_empty_order","无库存预定");

    private UserOrderTypeEnum(String code,String cName){
        this.code = code;
        this.cName = cName;

    }

    private String code;
    private String cName;


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
