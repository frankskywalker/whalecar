package com.whalecar.domain;

import java.io.Serializable;

/**
 * Created by ruihuang on 13-9-4.
 */
public class CarModelLv1Image implements Serializable {

    private Integer id;
    private Integer carModelLv1;
    private String imgPath;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarModelLv1() {
        return carModelLv1;
    }

    public void setCarModelLv1(Integer carModelLv1) {
        this.carModelLv1 = carModelLv1;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
