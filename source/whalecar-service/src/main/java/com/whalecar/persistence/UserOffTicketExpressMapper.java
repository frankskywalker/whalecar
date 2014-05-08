package com.whalecar.persistence;

import com.whalecar.domain.UserOffTicketExpress;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据carBrand的id获得carModelLv1的cname
     */
    public String findCarModelLv1Cname(int carBrand);

    /**
     * 根据carModelLv2的id获得carModelLv3的fullName
     */
    public String findCarModelLv3FullName(int carModelLv2);
}