package com.whalecar.service;

import com.whalecar.domain.UserSubmitPrice;
import com.whalecar.persistence.UserSubmitPriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruihuang on 13-9-2.
 */
@Controller
public class UserSubmitPriceService {

    @Autowired
    private UserSubmitPriceMapper userSubmitPriceMapper;

    /**
     * 创建UserSubmitPrice数据
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value="/saveUserSubmitPrice")
    public @ResponseBody boolean saveUserSubmitPrice(@RequestBody UserSubmitPrice userSubmitPrice){
        int i =userSubmitPriceMapper.createUserSubmitPrice(userSubmitPrice);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据用户查询已提交的价格
     *
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserSubmitPriceByUser")
    public @ResponseBody
    List<UserSubmitPrice> getUserSubmitPriceByUser(Integer userId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);
        return userSubmitPriceMapper.queryUserSubmitPriceByCondition(condition);
    }

    /**
     * 根据shop查询订单
     *
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserSubmitPriceByShop")
    public @ResponseBody List<UserSubmitPrice> getUserSubmitPriceByShop(Integer shopId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        return userSubmitPriceMapper.queryUserSubmitPriceByCondition(condition);
    }
}
