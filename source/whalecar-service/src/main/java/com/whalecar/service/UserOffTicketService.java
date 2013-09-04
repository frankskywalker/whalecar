package com.whalecar.service;

import com.whalecar.domain.UserOffTicket;
import com.whalecar.persistence.GenSeralnoMapper;
import com.whalecar.persistence.UserOffTicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruihuang on 13-9-2.
 */
@Controller
public class UserOffTicketService {

    @Autowired
    private GenSeralnoMapper genSeralnoMapper;
    @Autowired
    private UserOffTicketMapper userOffticketMapper;

    /**
     * 创建Off Ticket
     *
     * @param userOffTicket
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createUserOffTicket")
    public @ResponseBody
    UserOffTicket createUserOffTicket(@RequestBody UserOffTicket userOffTicket){
        //1.申请新的Off Ticket
        String ticketSn = genSeralnoMapper.genOffTicketByShop(userOffTicket.getShop());
        userOffTicket.setTicketSn(ticketSn);
        //2.保存
        userOffticketMapper.createUsertOffTicket(userOffTicket);
        //3.返回
        return userOffTicket;
    }

    /**
     * 根据用户查询
     *
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserOffTicketByUser")
    public @ResponseBody
    List<UserOffTicket> getUserOffTicketByUser(Integer userId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);
        return userOffticketMapper.queryUserOffTicketByCondition(condition);
    }

    /**
     * 根据shop查询
     *
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserOffTicketByShop")
    public @ResponseBody List<UserOffTicket> getUserOffTicketByShop(Integer shopId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        return userOffticketMapper.queryUserOffTicketByCondition(condition);
    }
}
