package com.whalecar.persistence;

import java.util.List;
import java.util.Map;

import com.whalecar.domain.UserOrder;

/**
 * Created by ruihuang on 13-8-23.
 */
public interface UserOrderMapper {

    /**
     * 创建Order数据
     * @return
     */
    public int createOrder(UserOrder userOrder);

    /**
     * 通过序号查询UserOrder
     * @param sn
     * @return
     */
    public UserOrder queryUserOrderBySn(String sn);

    /**
     * 通过condition查询UserOrder
     * @param condition
     * @return
     */
    public List<UserOrder> queryUserOrderByCondition(Map<String,Object> condition);

}
