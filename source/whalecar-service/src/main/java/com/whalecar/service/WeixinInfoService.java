package com.whalecar.service;

import com.whalecar.domain.WeixinInfo;
import com.whalecar.persistence.WeixinInfoMapper;
import com.whalecar.service.tools.BooleanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ruihuang on 14-5-26.
 */
@Controller
public class WeixinInfoService {

    @Autowired
    private WeixinInfoMapper weixinInfoMapper;

    @RequestMapping(value="/wiexininfo/save",method = RequestMethod.POST)
    public @ResponseBody BooleanResult save(@RequestBody WeixinInfo weixinInfo){
        int updateCount = weixinInfoMapper.save(weixinInfo);
        if(updateCount == 1){
            return new BooleanResult(true);
        }
        else{
            return new BooleanResult(false);
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

}
