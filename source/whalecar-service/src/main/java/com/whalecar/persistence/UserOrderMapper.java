package com.whalecar.persistence;

import java.util.List;
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

    public UserOrder queryUserOrderBySn(String sn);

    public List<UserOrder> queryUserOrderByUser(Integer userId);

}
