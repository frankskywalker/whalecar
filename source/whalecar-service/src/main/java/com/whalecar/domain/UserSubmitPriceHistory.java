package com.whalecar.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruihuang on 13-10-6.
 */
public class UserSubmitPriceHistory implements Serializable {
    private Integer id;
    private Integer userSubmitPrice;
    private Integer state;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserSubmitPrice() {
        return userSubmitPrice;
    }

    public void setUserSubmitPrice(Integer userSubmitPrice) {
        this.userSubmitPrice = userSubmitPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
