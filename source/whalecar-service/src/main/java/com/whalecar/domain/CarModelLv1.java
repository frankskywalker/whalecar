package com.whalecar.domain;

import java.io.Serializable;
import java.util.List;

// Generated 2013-7-5 22:40:38 by Hibernate Tools 3.4.0.CR1

/**
 * CarModelLv1 generated by hbm2java
 */
public class CarModelLv1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7803186654696850258L;
	private Integer id;
	private int carBrand;
    private String carBrandName;
	private Integer carSubBrand;
	private String carSubBrandName;
	private String cname;
	private String ename;
	private String imgPath;
	private Integer orderIndex;
	private String flagUseable;
    private String offprice;
    private String hotmodel;
    private String description;
	private String price;
	private List<CarModelLv2> carModelLv2List;

	public List<CarModelLv2> getCarModelLv2List() {
		return carModelLv2List;
	}

	public void setCarModelLv2List(List<CarModelLv2> carModelLv2List) {
		this.carModelLv2List = carModelLv2List;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public CarModelLv1() {
	}


    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public String getCarSubBrandName() {
        return carSubBrandName;
    }

    public void setCarSubBrandName(String carSubBrandName) {
        this.carSubBrandName = carSubBrandName;
    }

	public CarModelLv1(int carBrand, String cname, String ename,
			String imgPath, String flagUseable) {
		this.carBrand = carBrand;
		this.cname = cname;
		this.ename = ename;
		this.imgPath = imgPath;
		this.flagUseable = flagUseable;
	}

	public CarModelLv1(int carBrand, Integer carSubBrand, String cname,
			String ename, String imgPath, Integer orderIndex, String flagUseable) {
		this.carBrand = carBrand;
		this.carSubBrand = carSubBrand;
		this.cname = cname;
		this.ename = ename;
		this.imgPath = imgPath;
		this.orderIndex = orderIndex;
		this.flagUseable = flagUseable;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCarBrand() {
		return this.carBrand;
	}

	public void setCarBrand(int carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getCarSubBrand() {
		return this.carSubBrand;
	}

	public void setCarSubBrand(Integer carSubBrand) {
		this.carSubBrand = carSubBrand;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getFlagUseable() {
		return this.flagUseable;
	}

	public void setFlagUseable(String flagUseable) {
		this.flagUseable = flagUseable;
	}

    public String getOffprice() {
        return offprice;
    }

    public void setOffprice(String offprice) {
        this.offprice = offprice;
    }

    public String getHotmodel() {
        return hotmodel;
    }

    public void setHotmodel(String hotmodel) {
        this.hotmodel = hotmodel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
