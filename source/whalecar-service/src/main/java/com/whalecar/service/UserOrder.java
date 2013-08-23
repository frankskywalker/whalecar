package com.whalecar.service;

import com.whalecar.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by ruihuang on 13-8-23.
 */
@Controller
public class UserOrder {
    @Autowired
    private UserOrder userOrder;

    /**
     * 创建UserOrder
     *
     * @param userOrder
     * @return
     */
    public UserOrder createUserOrder(UserOrder userOrder){
        //1.申请新的order sn

        //2.保存
        //3.通过sn查出
    }


}
