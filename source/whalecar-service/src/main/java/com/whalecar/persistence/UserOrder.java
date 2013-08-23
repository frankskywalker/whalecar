package com.whalecar.persistence;

/**
 * Created by ruihuang on 13-8-23.
 */
public interface UserOrder {

    /**
     * 创建Order数据
     * @return
     */
    public int createOrder();

    public UserOrder queryUserOrderBySn(String sn);

}
