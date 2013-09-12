package com.whalecar.service;

import com.whalecar.domain.*;
import com.whalecar.persistence.GenSeralnoMapper;
import com.whalecar.persistence.ShopMapper;
import com.whalecar.persistence.UserOffTicketMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    @Autowired
    private ShopMapper shopMapper;

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
    List<UserOffTicketView> getUserOffTicketByUser(Integer userId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);
        List<UserOffTicket> list =  userOffticketMapper.queryUserOffTicketByCondition(condition);

        return buildUserOffTicketView(list);
    }

    /**
     * 根据shop查询
     *
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserOffTicketByShop")
    public @ResponseBody List<UserOffTicketView> getUserOffTicketByShop(Integer shopId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        List<UserOffTicket> list =  userOffticketMapper.queryUserOffTicketByCondition(condition);

        return buildUserOffTicketView(list);
    }

    /**
     * 组装UserSubmitPriceView的list
     * @param list
     * @return
     */
    private List<UserOffTicketView> buildUserOffTicketView(List<UserOffTicket> list){
        //查询对应的shopStockView组装UserSubmitPriceView
        List<UserOffTicketView> resultList = new ArrayList<UserOffTicketView>();
        for(UserOffTicket userOffTicket : list){
            //查询shopStockView
            int shopStockId = userOffTicket.getShopStock();
            ShopStockView shopStockView = shopMapper.queryShopStockViewById(shopStockId);

            //组装User Off Ticket View
            UserOffTicketView userOffTicketView = new UserOffTicketView();
            BeanUtils.copyProperties(userOffTicket, userOffTicketView);
            userOffTicketView.setShopStockView(shopStockView);

            resultList.add(userOffTicketView);
        }
        return resultList;
    }
}
