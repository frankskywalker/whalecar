package com.whalecar.persistence;

import com.whalecar.domain.UserOffTicket;
import com.whalecar.domain.UserOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by ruihuang on 13-9-2.
 */
public interface UserOffTicketMapper {

    /**
     * 创建UsertOffTicket数据
     * @return
     */
    public int createUsertOffTicket(UserOffTicket userOffTicket);

    /**
     * 根据condition查询UserOffTicket
     * @param condition
     * @return
     */
    public List<UserOffTicket> queryUserOffTicketByCondition(Map<String,Object> condition);
}
