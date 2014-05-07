package com.whalecar.persistence;

import com.whalecar.domain.UserOffTicketExpress;

import java.math.BigDecimal;

/**
 * Created by ruihuang on 14-5-6.
 */
public interface UserOffTicketExpressMapper {
    /**
     * 创建UsertOffTicket数据
     * @return
     */
    public int createUserOffTicketExpress(UserOffTicketExpress userOffTicketExpress);

    /**
     * 根据carModelLv3查询carPrice
     */
    public BigDecimal findCarPrice(int carModelLv3);

    /**
     * 根据carModelLv3查询factoryPrice
     */
    public BigDecimal findFactoryPrice(int id);
}
