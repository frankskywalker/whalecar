package com.whalecar.domain;

import java.util.Date;

/**
 * Created by ruihuang on 13-9-11.
 */
public class UserCarFavorite {
    private String brandCName;
    private String carModelLv1FullName;
    private int carModelLv1;
    private Date createDate;

    public String getBrandCName() {
        return brandCName;
    }

    public void setBrandCName(String brandCName) {
        this.brandCName = brandCName;
    }

    public String getCarModelLv1FullName() {
        return carModelLv1FullName;
    }

    public void setCarModelLv1FullName(String carModelLv1FullName) {
        this.carModelLv1FullName = carModelLv1FullName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCarModelLv1() {
        return carModelLv1;
    }

    public void setCarModelLv1(int carModelLv1) {
        this.carModelLv1 = carModelLv1;
    }
}
