package com.whalecar.service;

import java.util.List;
import com.whalecar.persistence.GenSeralnoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.whalecar.domain.UserOrder;
import com.whalecar.persistence.UserOrderMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by ruihuang on 13-8-23.
 */
@Controller
public class UserOrderService {
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private GenSeralnoMapper genSeralnoMapper;

    /**
     * 创建UserOrder
     *
     * @param userOrder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createUserOrder")
    public @ResponseBody
    UserOrder createUserOrder(@RequestBody UserOrder userOrder){
        //1.申请新的order sn
        String orderSn = genSeralnoMapper.genUserOrderSN();
        userOrder.setOrderSn(orderSn);
        //2.保存
        userOrderMapper.createOrder(userOrder);
        //3.通过sn查出
        return userOrderMapper.queryUserOrderBySn(orderSn);
    }

    /**
     * 根据用户订单
     *
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserOrderByUser")
    public @ResponseBody List<UserOrder> getUserOrderByUser(Integer userId){
        return userOrderMapper.queryUserOrderByUser(userId);
    }

}