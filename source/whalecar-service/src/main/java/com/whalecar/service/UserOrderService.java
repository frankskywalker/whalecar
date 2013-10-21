package com.whalecar.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whalecar.domain.ShopStock;
import com.whalecar.domain.UserSubmitPrice;
import com.whalecar.persistence.GenSeralnoMapper;
import com.whalecar.persistence.ShopMapper;
import com.whalecar.persistence.UserSubmitPriceMapper;
import com.whalecar.persistence.enums.UserOrderStateEnum;
import com.whalecar.persistence.enums.UserOrderTypeEnum;
import com.whalecar.persistence.enums.UserSubmitPriceStateEnum;
import com.whalecar.service.tools.BooleanResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.whalecar.domain.UserOrder;
import com.whalecar.persistence.UserOrderMapper;
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

        //2.判断库存是否满足
        int carOnOrderNum = shopStock.getCarOnOrderNum();
        if(carOnOrderNum < 1){
            logger.info("[create order] OnOrderNum is no enough,{shopStockID = {},userId = {},carOnOrderNum = {}},{}", userOrder.getShopStock(), userOrder.getId(),carOnOrderNum,logText);
            return new BooleanResult(false,"OnOrderNum is not enough");
        }
        logger.info("[create order] shop stock on order num check ok,carOnOrderNum = {},{}",carOnOrderNum, logText );

        //2.5 如果是用户议价订单，先查询价格，然后修改议价状态
        if(StringUtils.equals(userPrice,"true") && StringUtils.isNotBlank(userSubmitPriceId)){
            UserSubmitPrice userSubmitPrice = userSubmitPriceMapper.queryUserSubmitPriceById(Integer.valueOf(userSubmitPriceId));
            BigDecimal finalPrice = userSubmitPrice.getFinalPrice();
            userOrder.setOrderPrice(finalPrice);
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("id",userSubmitPriceId);
            params.put("state", UserSubmitPriceStateEnum.create_car_order);
            userSubmitPriceMapper.updateState(params);
            logger.info("[create order] user price update ok,{}", logText );
        }

        //3.修改库存(库存减1)
        shopMapper.updateShopStockOnOrderNum(userOrder.getShopStock(),carOnOrderNum - 1);
        logger.info("[create order] shop stock on order num update ok,{}", logText );

        //4.申请新的order sn
        String orderSn = genSeralnoMapper.genUserOrderSN();
        userOrder.setOrderSn(orderSn);
        logger.info("[create order] gen user order sn ok,sn = {},{}",orderSn, logText );

        //4.5 根据订单类型确定订单的初始状态：
        // 支付定金订单初始状态是等待付款。无支付定金订单初始状态是等待确认。
        if(StringUtils.equals(UserOrderTypeEnum.pay_order.getCode(),userOrder.getOrderType()) ){
            userOrder.setOrderState(UserOrderStateEnum.waiting_pay.getCode());
        }
        else if(StringUtils.equals(UserOrderTypeEnum.not_pay_order.getCode(),userOrder.getOrderType())){
            userOrder.setOrderState(UserOrderStateEnum.order_succ.getCode());
        }
        logger.info("[create order] init order state finish.orderType= {},init state={}.{}"
                   ,userOrder.getOrderType(),userOrder.getOrderState(), logText );

        //5.创建订单
        userOrderMapper.createOrder(userOrder);
        logger.info("[create order] save order ok,{}", logText );

        //6.通过sn查出
        UserOrder userOrderResult =  userOrderMapper.queryUserOrderBySn(orderSn);
        logger.info("[create order] query by sn ok,{}", logText );

        //7.组装并返回
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
    public @ResponseBody List<UserOrder> getUserOrderByUser(Integer userId){
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
    public @ResponseBody List<UserOrder> getUserOrderByShop(Integer shopId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        return userOrderMapper.queryUserOrderByCondition(condition);
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