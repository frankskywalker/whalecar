package com.whalecar.service;

import com.whalecar.persistence.UserCarMapper;
import com.whalecar.service.tools.BooleanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by sf on 14-3-20.
 */

@Controller
public class UserCarService {
    @Autowired
    private UserCarMapper userCarMapper;

    /**
     * 添加User_Car数据
     * */
    @RequestMapping(method = RequestMethod.POST,value = "addUserCar")
    public @ResponseBody
    Boolean addUserCar(@RequestBody Map<String,Object> map) {
        int i = userCarMapper.addUserCar(map);
        if(i != 0){
            return true;
        }else {
            return false;
        }
    }

 }
