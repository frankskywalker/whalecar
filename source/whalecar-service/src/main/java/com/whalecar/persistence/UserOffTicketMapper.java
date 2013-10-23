package com.whalecar.persistence;

import com.whalecar.domain.UserOffTicket;
import com.whalecar.domain.UserOffTicketView;
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
    public List<UserOffTicketView> queryUserOffTicketByCondition(Map<String,Object> condition);


    /**
     * 更新UserOffTicketState
     * @param condition
     */
    public int updateUserOffTicketState(Map<String,Object> condition);

    /**
     * query by id
     *
     * @param id
     * @return
     */
    public UserOffTicket queryUserOffTicketById(String id);
}
