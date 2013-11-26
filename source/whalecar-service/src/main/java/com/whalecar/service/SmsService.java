package com.whalecar.service;

import cn.emay.sdk.client.api.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by ruihuang on 13-11-14.
 */
@Controller
public class SmsService {

    Logger logger = LoggerFactory.getLogger(SmsService.class);

    private static Client client=null;

    /**
     * 即时发送短信
     *
     * @param mobiles
     * @param smsContent
     * @return
     */
    public boolean sendSMS(String[] mobiles,String smsContent){
        //default priority is 3
        int smsPriority = 3;
        int result = getClient().sendSMS(mobiles,smsContent,smsPriority);
        //如果错误代码为-1107或17，说明未激活，进行激活并重发
        if(result == -1107 || result == 17){
            ResourceBundle bundle= PropertyResourceBundle.getBundle("sms-config");
            getClient().registEx(bundle.getString("key"));
            result = getClient().sendSMS(mobiles,smsContent,smsPriority);
        }
        if(result == 0){
            logger.info("短信发送成功，电话号码:{},短信内容:{}",mobiles,smsContent);
            return true;
        }
        else{
            logger.warn("短信发送失败，错误代码:{},电话号码:{},短信内容:{}",result,mobiles,smsContent);
            return false;
        }
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
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddhhmmss");
        int result = getClient().sendScheduledSMS(mobiles,smsContent,sf.format(sendTime));
        if(result == 0){
            logger.info("短信发送成功，电话号码:{},短信内容:{}",mobiles,smsContent);
            return true;
        }
        else{
            logger.warn("短信发送失败，错误代码:{},电话号码:{},短信内容:{}",result,mobiles,smsContent);
            return false;
        }
    }

    /**
     * 生成100000 ~ 999999之间的随机数
     * @return
     */
    public String genAuthCode(){
        return String.valueOf((int)(Math.random()*899999) + 100000);
    }

    private synchronized  Client getClient(){
        ResourceBundle bundle= PropertyResourceBundle.getBundle("sms-config");
        if(client==null){
            try {
                client=new Client(bundle.getString("softwareSerialNo"),bundle.getString("key"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }
}
