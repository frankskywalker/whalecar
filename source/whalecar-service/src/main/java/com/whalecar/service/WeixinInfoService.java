package com.whalecar.service;

import com.whalecar.domain.User;
import com.whalecar.domain.WeixinInfo;
import com.whalecar.persistence.UserMapper;
import com.whalecar.persistence.WeixinInfoMapper;
import com.whalecar.service.tools.BooleanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by ruihuang on 14-5-26.
 */
@Controller
public class WeixinInfoService {

    @Autowired
    private WeixinInfoMapper weixinInfoMapper;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value="/wiexininfo/forward",method = RequestMethod.GET)
    public @ResponseBody BooleanResult forward(String wxOpenId,String userName,String sex){
        WeixinInfo weixinInfoCheckObj = this.queryByOpenId(wxOpenId);
        if(weixinInfoCheckObj == null){
            WeixinInfo weixinInfo = new WeixinInfo();
            weixinInfo.setCreateDate(new Date());
            weixinInfo.setUserName(userName);
            weixinInfo.setSex(sex);
            weixinInfo.setOpenId(wxOpenId);
            int updateCount = weixinInfoMapper.save(weixinInfo);
            if(updateCount == 1){
                return new BooleanResult(true);
            }
            else{
                return new BooleanResult(false);
            }
        }
        else
        {
            return new BooleanResult(true);
        }
    }

    @RequestMapping(value="/wiexininfo/save",method = RequestMethod.GET)
    public @ResponseBody BooleanResult save(String wxOpenId){
        WeixinInfo weixinInfoCheckObj = this.queryByOpenId(wxOpenId);
        User user = userMapper.queryUserByWxOpenId(wxOpenId);
        if(weixinInfoCheckObj == null){
            WeixinInfo weixinInfo = new WeixinInfo();
            weixinInfo.setCreateDate(new Date());
            weixinInfo.setUserName(user.getUserName());
            weixinInfo.setTel(user.getUserTel());
            weixinInfo.setOpenId(wxOpenId);
            int updateCount = weixinInfoMapper.save(weixinInfo);
            if(updateCount == 1){
                return new BooleanResult(true);
            }
            else{
                return new BooleanResult(false);
            }
        }
        else
        {
            return new BooleanResult(true);
        }
    }

    @RequestMapping(value="/wiexininfo/updateForwardCount",method = RequestMethod.GET)
    public @ResponseBody BooleanResult updateForwardCount(String openId){
        int updateCount = weixinInfoMapper.updateForwardCount(openId);
        if(updateCount == 1){
            return new BooleanResult(true);
        }
        else{
            return new BooleanResult(false);
        }
    }

    @RequestMapping(value="/wiexininfo/queryByOpenId",method = RequestMethod.GET)
    public @ResponseBody WeixinInfo queryByOpenId(String openId){
        return weixinInfoMapper.queryByOpenId(openId);
    }

    @RequestMapping(value="/weixininfo/queryWithRank",method = RequestMethod.GET)
    public @ResponseBody
    List<WeixinInfo> queryAllWithRank(){
        return weixinInfoMapper.queryAllWithRank();
    }


    @RequestMapping(value="/wxDetailSave",method = RequestMethod.POST)
    public @ResponseBody BooleanResult wxDetailSave(String name, String tel, String brandName , String subBrandName){
        String car = brandName + " " + subBrandName;
        int updateCount = weixinInfoMapper.insertWxDetail(name, tel, car);
        if(updateCount == 1){
            return new BooleanResult(true);
        }
        else{
            return new BooleanResult(false);
        }
    }

}
