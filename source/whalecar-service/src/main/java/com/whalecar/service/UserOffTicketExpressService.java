package com.whalecar.service;

import com.whalecar.domain.CarBrand;
import com.whalecar.domain.CarModelLv1;
import com.whalecar.domain.CarModelLv3;
import com.whalecar.domain.UserOffTicketExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.whalecar.persistence.UserOffTicketExpressMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    BigDecimal findCarPrice(@RequestBody Map<String,String> map){
        String carModelLv3 = map.get("carModelLv3");
        return userOffTicketExpressMapper.findCarPrice(Integer.valueOf(carModelLv3));
    }

    /**
     * 根据carModelLv3查询factoryPrice
     */
    @RequestMapping(method = RequestMethod.POST, value = "/findFactoryPrice")
    public @ResponseBody
    String findFactoryPrice(@RequestBody Map<String,String> map){
        String carModelLv3 = map.get("carModelLv3");
        return String.valueOf(userOffTicketExpressMapper.findFactoryPrice(Integer.valueOf(carModelLv3)));
    }

    /**
     * 根据id查询carBrand
     */
    @RequestMapping(method = RequestMethod.POST, value = "/findCarBrand")
    public @ResponseBody
    List<CarBrand> findCarBrand(){
        return userOffTicketExpressMapper.findCarBrand();
    }

    /**
     * 根据carBrand查询carModelLv1
     */
    @RequestMapping(method = RequestMethod.POST, value = "/findCarModelLv1")
    public @ResponseBody
    List<CarModelLv1> findCarModelLv1(@RequestBody Map<String,String> map){
        String carBrand = map.get("carBrand");
        return userOffTicketExpressMapper.findCarModelLv1(Integer.valueOf(carBrand));
    }

    /**
     * 根据carModelLv1的id查询carModelLv3
     */
    @RequestMapping(method =  RequestMethod.POST, value = "/findCarModelLv3")
    public @ResponseBody
    List<CarModelLv3> findCarModelLv3(@RequestBody Map<String,String> map){
        String id = map.get("id");
        return userOffTicketExpressMapper.findCarModelLv3(Integer.valueOf(id));
    }
}
