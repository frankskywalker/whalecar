package com.whalecar.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruihuang on 13-9-25.
 */
public class CarBrandWithSubBrandView implements Serializable {
    private String brandName;
    private String brandId;

    private List<CarModelLv1> carModelLv1List = new ArrayList<CarModelLv1>();

    public List<CarModelLv1> getCarModelLv1List() {
        return carModelLv1List;
    }

    public void setCarModelLv1List(List<CarModelLv1> carModelLv1List) {
        this.carModelLv1List = carModelLv1List;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
