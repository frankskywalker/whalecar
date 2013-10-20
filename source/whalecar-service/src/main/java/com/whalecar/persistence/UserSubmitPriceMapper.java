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

    /**
     * 更新处理状态
     * @param condition
     * @return
     */
    public int updateState(Map<String,Object> condition);

    /**
     * 根据id查询UserSubmitPrice 对象
     * @param id
     * @return
     */
    public UserSubmitPrice queryUserSubmitPriceById(Integer id);
}
