package com.whalecar.service;

import com.whalecar.domain.ShopStockView;
import com.whalecar.domain.UserSubmitPrice;
import com.whalecar.domain.UserSubmitPriceView;
import com.whalecar.persistence.ShopMapper;
import com.whalecar.persistence.UserSubmitPriceMapper;
import com.whalecar.service.tools.BooleanResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    @Autowired
    private ShopMapper shopMapper;

    /**
     * 创建UserSubmitPrice数据
     *
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
    List<UserSubmitPriceView> getUserSubmitPriceByUser(Integer userId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);

        //查询User Submit Price 列表
        List<UserSubmitPrice> list = userSubmitPriceMapper.queryUserSubmitPriceByCondition(condition);

        return buildUserSubmitPriceView(list);
    }

    /**
     * 根据shop查询订单
     *
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserSubmitPriceByShop")
    public @ResponseBody List<UserSubmitPriceView> getUserSubmitPriceByShop(Integer shopId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        //查询User Submit Price 列表
        List<UserSubmitPrice> list = userSubmitPriceMapper.queryUserSubmitPriceByCondition(condition);

        return buildUserSubmitPriceView(list);
    }


    /**
     * 更新处理状态
     *
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/changeUserSubmitPriceProcessState")
    public @ResponseBody
    BooleanResult changeUserSubmitPriceProcessState(@RequestBody Map<String,Object> params){
        int updateCount = userSubmitPriceMapper.updateState(params);
        if(updateCount == 1){
            return new BooleanResult(true);
        }
        else{
            return new BooleanResult(false);
        }
    }


    /**
     * 组装UserSubmitPriceView的list
     *
     * @param list
     * @return
     */
    private List<UserSubmitPriceView> buildUserSubmitPriceView(List<UserSubmitPrice> list){
        //查询对应的shopStockView组装UserSubmitPriceView
        List<UserSubmitPriceView> resultList = new ArrayList<UserSubmitPriceView>();
        for(UserSubmitPrice userSubmitPrice : list){
            //查询shopStockView
            int shopStockId = userSubmitPrice.getShopStock();
            ShopStockView shopStockView = shopMapper.queryShopStockViewById(shopStockId);

            //组装User Submit Price View
            UserSubmitPriceView userSubmitPriceView = new UserSubmitPriceView();
            BeanUtils.copyProperties(userSubmitPrice, userSubmitPriceView);
            userSubmitPriceView.setShopStockView(shopStockView);

            resultList.add(userSubmitPriceView);
        }
        return resultList;
    }

}