package com.whalecar.persistence;

import com.whalecar.domain.User;
import com.whalecar.domain.UserOffTicketManager;
import com.whalecar.domain.UserOrderManager;
import com.whalecar.domain.UserSubmitPriceManager;

import java.util.List;

/**
 * Created by ruihuang on 14-2-26.
 */
public interface ManagerMapper {

    /**
     * 查询优惠价格购买表格
     * @return
     */
    public List<UserOffTicketManager> queryUserOffTicket();


    /**
     * 查询用户订单表格
     * @return
     */
    public List<UserOrderManager> queryUserOrder();


    /**
     * 查询用户直接提交的表格
     * @return
     */
    public List<UserSubmitPriceManager> queryUserSubmitPrice();


    /**
     * 查找所有用户
     * @return
     */
    public List<User> queryUser();
}
