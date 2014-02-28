package com.whalecar.service;

import com.whalecar.domain.User;
import com.whalecar.domain.UserOffTicketManager;
import com.whalecar.domain.UserOrderManager;
import com.whalecar.domain.UserSubmitPriceManager;
import com.whalecar.persistence.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ruihuang on 14-2-26.
 */
@Controller
public class ManagerService {

    @Autowired
    private ManagerMapper managerMapper;


    /**
     * 查询优惠价格购买表格
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/queryUserOffTicket")
    public @ResponseBody
    List<UserOffTicketManager> queryUserOffTicket(){
        return managerMapper.queryUserOffTicket();
    }


    /**
     * 查询用户订单表格
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/queryUserOrder")
    public @ResponseBody
    List<UserOrderManager> queryUserOrder(){
        return managerMapper.queryUserOrder();
    }


    /**
     * 查找用户提交价格表格
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/queryUserSubmitPrice")
    public @ResponseBody
    List<UserSubmitPriceManager> queryUserSubmitPrice(){
      return managerMapper.queryUserSubmitPrice();
    }


    /**
     * 查找所有用户
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/manager/managerUser")
    public @ResponseBody
    List<User> queryUser(){
        return managerMapper.queryUser();
    }
}
