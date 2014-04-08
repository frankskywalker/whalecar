package com.whalecar.service;

import com.whalecar.domain.UserCar;
import com.whalecar.persistence.GenSeralnoMapper;
import com.whalecar.persistence.UserCarMapper;
import com.whalecar.service.tools.BooleanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by sf on 14-3-20.
 */

@Controller
public class UserCarService {
    @Autowired
    private UserCarMapper userCarMapper;
    @Autowired
    private GenSeralnoMapper genSeralnoMapper;
    @Autowired
    private SmsService smsService;


    /**
     * 添加User_Car添加优惠码
     * */
    @RequestMapping(method = RequestMethod.POST,value = "/addUserCar")
    public @ResponseBody
    UserCar addUserCar(@RequestBody UserCar userCar) {
        //1.申请新的promoCode
        String newPromoCode = genSeralnoMapper.genOffTicketByShop(0);
        userCar.setPromoCode(newPromoCode);
        //2.发送短信
        Calendar cal = Calendar.getInstance();
        //延时4小时发送
        cal.add(Calendar.HOUR,4);

        userCar.setCreateDate(new Date());
        smsService.sendScheduledSMS(new String[]{userCar.getPhoneNum()}
                ,"优惠码：" + newPromoCode + ",请在店内议价后出示优惠码进行优惠。退订回复TD"
                ,cal.getTime());
        //3.保存
        userCarMapper.addUserCar(userCar);
        //4.返回
        return userCar;
    }

 }
