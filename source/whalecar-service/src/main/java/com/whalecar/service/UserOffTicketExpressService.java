package com.whalecar.service;

import com.whalecar.domain.UserOffTicketExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.whalecar.persistence.UserOffTicketExpressMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * Created by ruihuang on 14-5-7.
 */
@Controller
public class UserOffTicketExpressService {
    @Autowired
    private UserOffTicketExpressMapper userOffTicketExpressMapper;

    /**
     * 创建Off Ticket Express
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createUserOffTicketExpress")
    public @ResponseBody
    UserOffTicketExpress createUserOffTicketExpress(@RequestBody UserOffTicketExpress userOffTicketExpress){
        userOffTicketExpressMapper.createUserOffTicketExpress(userOffTicketExpress);
        return userOffTicketExpress;
    }

    /**
     * 根据carModelLv3查询carPrice
     */
    @RequestMapping(method = RequestMethod.POST, value = "/findCarPrice")
    public @ResponseBody
    BigDecimal findCarPrice(@RequestBody int carModelLv3){
        return userOffTicketExpressMapper.findCarPrice(carModelLv3);
    }

    /**
     * 根据carModelLv3查询factoryPrice
     */
    @RequestMapping(method = RequestMethod.POST, value = "/findFactoryPrice")
    public @ResponseBody
    BigDecimal findFactoryPrice(@RequestBody int id){
        return userOffTicketExpressMapper.findFactoryPrice(id);
    }
}
