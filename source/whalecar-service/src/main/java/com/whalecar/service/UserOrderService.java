package com.whalecar.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whalecar.domain.*;
import com.whalecar.persistence.*;
import com.whalecar.persistence.enums.UserOrderStateEnum;
import com.whalecar.persistence.enums.UserOrderTypeEnum;
import com.whalecar.persistence.enums.UserSubmitPriceStateEnum;
import com.whalecar.service.tools.BooleanResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private UserSubmitPriceMapper userSubmitPriceMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SmsService smsService;

    private Logger logger = LoggerFactory.getLogger(UserOrderService.class);

    /**
     * 创建UserOrder
     *
     * @param userOrder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createUserOrder")
    public @ResponseBody
    BooleanResult createUserOrder(@RequestBody UserOrder userOrder,String userPrice,String userSubmitPriceId){

        String logText ="{shopStockID = " + userOrder.getShopStock() + ",userId=" + userOrder.getUser() + "}";
        logger.info("[create order] start create order,{}", logText );

        //1.锁定库存
        ShopStock shopStock = shopMapper.queryShopStockByIdWithLock(userOrder.getShopStock());
        logger.info("[create order] lock shop stock ok,{}", logText );

        //2.判断库存是否满足(如果无库存，进入无库存订单流程)
        int carOnOrderNum = shopStock.getCarOnOrderNum();
        int carOnHandNum = shopStock.getCarOnHandNum();
        //无库存，设置orderType = stock_empty_order
        if(carOnOrderNum + carOnHandNum == 0){
            userOrder.setOrderType(UserOrderTypeEnum.stock_empty_order.getCode());
            logger.info("[create order] OnOrderNum is no enough,go to waiting ,{shopStockID = {},userId = {}},{}", userOrder.getShopStock(), userOrder.getId(),logText);
        }
        //有库存，更新库存数量
        else{
            logger.info("[create order] shop stock on order num check ok,carOnOrderNum = {},{}",carOnOrderNum, logText );
            //3.修改库存(库存减1)
            if(carOnOrderNum > 0){
                shopMapper.updateShopStockOnOrderNum(userOrder.getShopStock(),carOnOrderNum - 1);
            }
            else if (carOnHandNum > 0 ){
                shopMapper.updateShopStockOnHandNum(userOrder.getShopStock(),carOnHandNum - 1);
            }
            else{
                logger.error("[create order] update order num has something wrong,{}", logText );
                return new BooleanResult(false,"update order num has something wrong.");
            }

            logger.info("[create order] shop stock on order num update ok,{}", logText );
        }



        //2.5 如果是用户议价订单，先查询价格，然后修改议价状态
        if(StringUtils.equals(userPrice,"true") && StringUtils.isNotBlank(userSubmitPriceId)){

            //设置价格
            UserSubmitPrice userSubmitPrice = userSubmitPriceMapper.queryUserSubmitPriceById(Integer.valueOf(userSubmitPriceId));
            BigDecimal finalPrice = userSubmitPrice.getFinalPrice();
            userOrder.setOrderPrice(finalPrice);

            //组装参数
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("id",userSubmitPriceId);
            params.put("state", UserSubmitPriceStateEnum.create_car_order);

            //更新userSubmitPrice
            userSubmitPriceMapper.updateState(params);
            logger.info("[create order] user price update ok,{}", logText );
        }

        //4.申请新的order sn
        String orderSn = genSeralnoMapper.genUserOrderSN();
        userOrder.setOrderSn(orderSn);
        logger.info("[create order] gen user order sn ok,sn = {},{}",orderSn, logText );

        //5 根据订单类型确定订单的初始状态：
        // 支付定金订单初始状态是等待付款。无支付定金订单初始状态是等待确认。
        if(StringUtils.equals(UserOrderTypeEnum.pay_order.getCode(),userOrder.getOrderType()) ){
            userOrder.setOrderState(UserOrderStateEnum.waiting_pay.getCode());
        }
        else if(StringUtils.equals(UserOrderTypeEnum.not_pay_order.getCode(),userOrder.getOrderType())){
            userOrder.setOrderState(UserOrderStateEnum.order_succ.getCode());
        }
        else if(StringUtils.equals(UserOrderTypeEnum.stock_empty_order.getCode(),userOrder.getOrderType())){
            userOrder.setOrderState(UserOrderStateEnum.waiting_delivery.getCode());
        }
        logger.info("[create order] init order state finish.orderType= {},init state={}.{}"
                   ,userOrder.getOrderType(),userOrder.getOrderState(), logText );

        //6 发送创建订单短信
        User user = userMapper.queryUserById(userOrder.getUser());
        StringBuilder smsContent = new StringBuilder();
        smsContent.append("客户您好，您已成功预定了");
        smsContent.append(userOrder.getOrderTitle());
        smsContent.append("请在3天内到店内办理提车。退订回复TD【梯卡汽车】");
        smsService.sendSMS(new String[]{user.getUserTel()},smsContent.toString());

        //7.创建订单
        userOrderMapper.createOrder(userOrder);
        logger.info("[create order] save order ok,{}", logText );

        //8.通过sn查出
        UserOrder userOrderResult =  userOrderMapper.queryUserOrderBySn(orderSn);
        logger.info("[create order] query by sn ok,{}", logText );

        //9.组装并返回
        BooleanResult result = new BooleanResult(true,null);
        result.getResultMap().put("userOrder",userOrderResult);
        return result;
    }

    /**
     * 根据用户查询订单
     *
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserOrderByUser")
    public @ResponseBody List<UserOrderView> getUserOrderByUser(Integer userId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);
        return userOrderMapper.queryUserOrderByCondition(condition);
    }

    /**
     * 根据shop查询订单
     *
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserOrderByShop")
    public @ResponseBody List<UserOrderView> getUserOrderByShop(Integer shopId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        return userOrderMapper.queryUserOrderByCondition(condition);
    }

    /**
     * 根据用户查询缺货订单
     *
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getStockEmptyUserOrderByUser")
    public @ResponseBody List<UserOrderView> getStockEmptyUserOrderByUser(Integer userId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);
        return userOrderMapper.queryStockEmptyUserOrderByCondition(condition);
    }

    /**
     * 根据shop查询缺货订单
     *
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getStockEmptyUserOrderByShop")
    public @ResponseBody List<UserOrderView> getStockEmptyUserOrderByShop(Integer shopId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        return userOrderMapper.queryStockEmptyUserOrderByCondition(condition);
    }

    /**
     * 更新处理状态
     *
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/changeUserOrderProcessState")
    public @ResponseBody
    BooleanResult changeUserOrderProcessState(@RequestBody Map<String,Object> params){
        int updateCount = userOrderMapper.updateState(params);
        if(updateCount == 1){
            return new BooleanResult(true);
        }
        else{
            return new BooleanResult(false);
        }
    }
}