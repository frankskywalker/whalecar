package com.whalecar.service;

import com.whalecar.domain.ShopStockView;
import com.whalecar.domain.User;
import com.whalecar.domain.UserSubmitPrice;
import com.whalecar.domain.UserSubmitPriceView;
import com.whalecar.persistence.ShopMapper;
import com.whalecar.persistence.UserMapper;
import com.whalecar.persistence.UserSubmitPriceMapper;
import com.whalecar.persistence.enums.UserSubmitPriceStateEnum;
import com.whalecar.service.tools.BooleanResult;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserMapper userMapper;

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
        if(userId == null){
            return null;
        }
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);

        //查询User Submit Price 列表
        List<UserSubmitPriceView> list = userSubmitPriceMapper.queryUserSubmitPriceByCondition(condition);
        buildUserSubmitPriceView(list);

        return list;
    }

    /**
     * 根据shop查询订单
     *
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserSubmitPriceByShop")
    public @ResponseBody List<UserSubmitPriceView> getUserSubmitPriceByShop(Integer shopId){
        if(shopId == null){
            return null;
        }
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        //查询User Submit Price 列表
        List<UserSubmitPriceView> list = userSubmitPriceMapper.queryUserSubmitPriceByCondition(condition);
        buildUserSubmitPriceView(list);
        return list;
    }

    /**
     * id和user查询订单
     *
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserSubmitPriceByUserAndId")
    public @ResponseBody UserSubmitPriceView getUserSubmitPriceByShop(Integer userId,Integer id){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);
        condition.put("id",id);
        //查询User Submit Price 列表
        List<UserSubmitPriceView> list = userSubmitPriceMapper.queryUserSubmitPriceByCondition(condition);
        if(list != null && list.size() != 0){
            return list.get(0);
        }
        return null;
    }


    /**
     * 更新处理状态
     *
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/changeUserSubmitPriceState")
    public @ResponseBody
    BooleanResult changeUserSubmitPriceState(@RequestBody Map<String,Object> params){
        //1.确认状态合法性
        String state = String.valueOf(params.get("state"));
        String id = String.valueOf(params.get("id"));
        String shopPrice = String.valueOf(params.get("shopPrice"));
        if(UserSubmitPriceStateEnum.valueOf(state) == null){
            return new BooleanResult(false);
        }
        if(StringUtils.isBlank(id)){
            return new BooleanResult(false);
        }
        //2.根据状态确认是否要发送短信
        //a.shop所给的确认反馈需要发短信
        UserSubmitPrice userSubmitPrice = userSubmitPriceMapper.queryUserSubmitPriceById(Integer.valueOf(id));
        User user = userMapper.queryUserById(userSubmitPrice.getUser());
        ShopStockView shopStockView = shopMapper.queryShopStockViewById(userSubmitPrice.getShopStock());
        if(UserSubmitPriceStateEnum.shop_agree.valueOf(state) == UserSubmitPriceStateEnum.shop_agree){
            StringBuilder smsContent = new StringBuilder();
            smsContent.append("您好，您提交的");
            smsContent.append(shopStockView.getCarBrandCname());
            smsContent.append(shopStockView.getCarModelLv1Cname());
            smsContent.append(shopStockView.getCarModelLv3FullName());
            smsContent.append("车型的意向价格为");
            smsContent.append(userSubmitPrice.getUserPrice());
            smsContent.append("万元，4S店已回复同意了您提交的价格。请尽快登录网站生成订单。退订回复TD【梯卡汽车】");
            smsService.sendSMS(new String[]{user.getUserTel()},smsContent.toString());
        }
        //b.shop所回复的价格需要发送短信
        if(UserSubmitPriceStateEnum.shop_commit.valueOf(state) == UserSubmitPriceStateEnum.shop_commit){
            StringBuilder smsContent = new StringBuilder();
            smsContent.append("您好，您提交的");
            smsContent.append(shopStockView.getCarBrandCname());
            smsContent.append(shopStockView.getCarModelLv1Cname());
            smsContent.append(shopStockView.getCarModelLv3FullName());
            smsContent.append("车型的意向价格为");
            smsContent.append(userSubmitPrice.getUserPrice());
            smsContent.append("万元，4S店已经回复，价格为");
            smsContent.append(shopPrice);
            smsContent.append("万元，如您同意此价格，请尽快登录网站生成订单。退订回复TD【梯卡汽车】");
            smsService.sendSMS(new String[]{user.getUserTel()},smsContent.toString());
        }

        //3.更新数据
        int updateCount = userSubmitPriceMapper.updateState(params);
        if(updateCount == 1){
            return new BooleanResult(true);
        }
        else{
            return new BooleanResult(false);
        }
    }

    /**
     * get UserSubmitPrice by id
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/getUserSubmitPriceById")
    public @ResponseBody UserSubmitPrice getUserSubmitPriceById(Integer id){
        return userSubmitPriceMapper.queryUserSubmitPriceById(id);
    }

    /**
     * 组装UserSubmitPriceView的list
     *
     * @param list
     * @return
     */
    private void buildUserSubmitPriceView(List<UserSubmitPriceView> list){
        //查询对应的shopStockView组装UserSubmitPriceView
        List<UserSubmitPriceView> resultList = new ArrayList<UserSubmitPriceView>();
        for(UserSubmitPriceView userSubmitPrice : list){
            //查询shopStockView
            int shopStockId = userSubmitPrice.getShopStock();
            ShopStockView shopStockView = shopMapper.queryShopStockViewById(shopStockId);

            //组装User Submit Price View
            userSubmitPrice.setShopStockView(shopStockView);

        }
    }

}