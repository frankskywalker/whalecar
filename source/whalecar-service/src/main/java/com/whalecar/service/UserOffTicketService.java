package com.whalecar.service;

import com.whalecar.domain.*;
import com.whalecar.persistence.GenSeralnoMapper;
import com.whalecar.persistence.ShopMapper;
import com.whalecar.persistence.UserOffTicketMapper;
import com.whalecar.persistence.enums.UserOffTicketStateEnum;
import com.whalecar.service.tools.BooleanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
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
    @Autowired
    private SmsService smsService;

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
        userOffTicket.setState(UserOffTicketStateEnum.has_send.getCode());
        //2.发送短信
        Calendar cal = Calendar.getInstance();
        //延时4小时发送
        cal.add(Calendar.HOUR,4);
        smsService.sendScheduledSMS(new String[]{userOffTicket.getPhoneNum()}
                ,"优惠码：" + ticketSn + ",请在店内议价后出示优惠码进行优惠。退订回复TD【梯卡网】"
                ,cal.getTime());
        //3.保存
        userOffticketMapper.createUsertOffTicket(userOffTicket);
        //4.返回
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
        if(userId == null){
            return null;
        }
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("userId",userId);
        List<UserOffTicketView> list =  userOffticketMapper.queryUserOffTicketByCondition(condition);

        buildUserOffTicketView(list);

        return list;
    }

    /**
     * 根据shop查询
     *
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserOffTicketByShop")
    public @ResponseBody List<UserOffTicketView> getUserOffTicketByShop(Integer shopId){
        if(shopId == null){
            return null;
        }
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("shopId",shopId);
        List<UserOffTicketView> list =  userOffticketMapper.queryUserOffTicketByCondition(condition);

        buildUserOffTicketView(list);

        return list;
    }

    /**
     * 根据条件更新UserOffTicket state
     * @param condition {id:,state,billSn}
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/changeUserOffTicketState")
    public @ResponseBody BooleanResult changeUserOffTicketState(@RequestBody Map<String,Object> condition){
        int sum = userOffticketMapper.updateUserOffTicketState(condition);
        if(sum == 1){
            return new BooleanResult(true);
        }
        else{
            return new BooleanResult(false);
        }
    }

    /**
     * query by id
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value="/getUserOffTicketById")
    public @ResponseBody UserOffTicket getUserOffTicketById(String id){
        return userOffticketMapper.queryUserOffTicketById(id);
    }


    /**
     * 组装UserSubmitPriceView的list
     * @param list
     * @return
     */
    private void buildUserOffTicketView(List<UserOffTicketView> list){
        //查询对应的shopStockView组装UserSubmitPriceView
        for(UserOffTicketView userOffTicket : list){
            //查询shopStockView
            int shopStockId = userOffTicket.getShopStock();
            ShopStockView shopStockView = shopMapper.queryShopStockViewById(shopStockId);

            //组装User Off Ticket View
            userOffTicket.setShopStockView(shopStockView);
        }
    }
}
