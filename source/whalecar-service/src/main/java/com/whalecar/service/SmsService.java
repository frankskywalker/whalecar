package com.whalecar.service;

import com.shcm.bean.ReplyBean;
import com.shcm.bean.SendStateBean;
import com.shcm.send.DataApi;
import com.shcm.send.OpenApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by ruihuang on 13-11-14.
 */
@Controller
public class SmsService {

    Logger logger = LoggerFactory.getLogger(SmsService.class);

    private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
    private static String sDataUrl = "http://smsapi.c123.cn/DataPlatform/DataApi";

    // 接口帐号
    private static final String account = "1001@500715620001";

    // 接口密钥
    private static final String authkey = "0E82875A26A0CAF78713A94A004E6421";

    // 通道组编号
    private static final int cgid = 785;

    // 默认使用的签名编号(未指定签名编号时传此值到服务器)
    private static final int csid = 0;

    /**
     * 即时发送短信
     *
     * @param mobiles
     * @param smsContent
     * @return
     */
    public boolean sendSMS(String[] mobiles,String smsContent){
        return sendScheduledSMS(mobiles,smsContent,null);
    }

    /**
     * 定时发送短信
     *
     * @param mobiles
     * @param smsContent
     * @param sendTime
     * @return
     */
    public boolean sendScheduledSMS(String[] mobiles, String smsContent, Date sendTime){
        // 发送参数
        OpenApi.initialzeAccount(sOpenUrl, account, authkey, cgid, csid);

        // 状态及回复参数
        DataApi.initialzeAccount(sDataUrl, account, authkey);

        // 取帐户余额
        double dReamin = OpenApi.getBalance();
        logger.info("短信可用余额: " + dReamin);

        // 发送短信
        try{
            String sSend = new String(smsContent.getBytes(), "UTF-8");
        }
        catch (Exception e){
            logger.warn(e.getMessage());
        }
        //如果发送时间为空，则表示即时发送
        String time = null;
        if(sendTime != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            time = format.format(sendTime);
        }

        int nRet = OpenApi.sendOnce(mobiles, smsContent, cgid, csid, time);

        if(nRet > 0)
        {
            logger.info("发送成功! => nRet:{},mobiles:{},smsContent:{},sendTime:",nRet,mobiles,smsContent,sendTime);
            return true;
        }
        else
        {
            logger.warn("发送失败! => nRet:{},mobiles:{},smsContent:{},sendTime:",nRet,mobiles,smsContent,sendTime);
            return false;
        }

    }
}
