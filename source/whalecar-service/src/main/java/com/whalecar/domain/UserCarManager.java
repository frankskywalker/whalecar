package com.whalecar.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wufei on 2014/7/1.
 */
public class UserCarManager implements Serializable {
    private static final long serialVersionUID = -5114094581321998769L;
    private String id;
    private String userName;
    private String phoneNum;
    private String carID;
    private String carName;
    private String createDate;
    private String promoCode;

    public  UserCarManager(String id,String userName,String phoneNum,String carID,String carName,String createDate,String promoCode){
        this.id = id;
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.carID = carID;
        this.carName = carName;
        this.createDate = createDate;
        this.promoCode = promoCode;

    }
    public String getCreateDate() {
        return this.createDate;
    }
    public String getId() {
        return this.id;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getPhoneNum(){
        return this.phoneNum;
    }
    public String getCarID(){
        return this.carID;
    }
    public String getCarName(){
        return this.carID;
    }
    public String getPromoCode(){
        return this.promoCode;
    }
}
