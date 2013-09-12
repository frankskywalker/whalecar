package com.whalecar.persistence;

import com.whalecar.domain.UserOffTicket;
import com.whalecar.domain.UserSubmitPrice;

import java.util.List;
import java.util.Map;

/**
 * Created by ruihuang on 13-9-2.
 */
public interface UserSubmitPriceMapper {
    /**
     * 创建UserSubmitPrice数据
     * @return
     */
    public int createUserSubmitPrice(UserSubmitPrice userSubmitPrice);

    /**
     * 根据condition查询UserSubmitPrice
     * @param condition
     * @return
     */
    public List<UserSubmitPrice> queryUserSubmitPriceByCondition(Map<String,Object> condition);
}