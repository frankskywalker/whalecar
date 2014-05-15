package com.whalecar.persistence;

import com.whalecar.domain.*;

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
    public BigDecimal findFactoryPrice(int carModelLv3);

    /**
     * 根据id查询carBrand
     */
    public List<CarBrand> findCarBrand();

    /**
     * 根据carBrand获得carModelLv1
     */
    public List<CarModelLv1> findCarModelLv1(int carBrand);

    /**
     * 根据carModelLv1的id获得carModelLv3
     */
    public List<CarModelLv3> findCarModelLv3(int id);
}