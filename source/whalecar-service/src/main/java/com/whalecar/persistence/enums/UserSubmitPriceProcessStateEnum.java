package com.whalecar.persistence.enums;

/**
 * 用户提交价格处理状态Enum
 *
 * Created by ruihuang on 13-9-17.
 */
public enum UserSubmitPriceProcessStateEnum {

    wait_process("等待处理"),
    finish_process("完成处理");

    private UserSubmitPriceProcessStateEnum(String stateCName){
        this.stateCName = stateCName;
    }

    private String stateCName;

    public String getStateCName() {
        return stateCName;
    }
}
