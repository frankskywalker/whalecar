package com.whalecar.persistence.enums;

/**
 * 优惠券处理状态Enum
 *
 * Created by ruihuang on 13-9-17.
 */
public enum UserOffTicketStateEnum {

    has_send("has_send","未使用")
    ,waiting_confirm("waiting_confirm","等待确认")
    ,finish("finish","已完成")
    ,out_of_date("out_of_date","已过期");

    private UserOffTicketStateEnum(String code,String stateCName){
        this.code = code;
        this.stateCName = stateCName;
    }

    private String stateCName;
    private String code;

    public String getStateCName() {
        return stateCName;
    }

    public void setStateCName(String stateCName) {
        this.stateCName = stateCName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
