package com.whalecar.persistence;

import java.util.List;
import java.util.Map;

import com.whalecar.domain.UserOrder;
import com.whalecar.domain.UserOrderView;

/**
 * Created by ruihuang on 13-8-23.
 */
public interface UserOrderMapper {

    /**
     * 创建Order数据
     *
     * @return
     */
    public int createOrder(UserOrder userOrder);

    /**
     * 通过序号查询UserOrder
     *
     * @param sn
     * @return
     */
    public UserOrder queryUserOrderBySn(String sn);

    /**
     * 通过condition查询UserOrder
     *
     * @param condition
     * @return
     */
    public List<UserOrderView> queryUserOrderByCondition(Map<String,Object> condition);

    /**
     * 通过condition查询StockEmptyUserOrder
     *
     * @param condition
     * @return
     */
    public List<UserOrderView> queryStockEmptyUserOrderByCondition(Map<String,Object> condition);

    /**
     * 更新处理状态
     *
     * @param condition
     * @return
     */
    public int updateState(Map<String,Object> condition);

    /**
     * 处理已经过期的订单，将订单状态更新为out of date
     */
    public void processUserOrderOutOfDate();

}
