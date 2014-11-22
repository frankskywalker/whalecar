package com.whalecar.domain;

import java.io.Serializable;
import java.util.List;

// Generated 2013-7-5 22:40:38 by Hibernate Tools 3.4.0.CR1

/**
 * CarModelLv2 generated by hbm2java
 */
public class CarModelLv2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 212554565260987336L;
	private Integer id;
	private int carModelLv1;
	private String carModelLv1Name;
	private String shortName;
	private String fullName;
	private Integer orderIndex;
	private String flagUseable;
    private List<CarModelLv3> carModelLv3List;

	public CarModelLv2() {
	}

	public CarModelLv2(int carModelLv1, String shortName, String fullName,
			String flagUseable) {
		this.carModelLv1 = carModelLv1;
		this.shortName = shortName;
		this.fullName = fullName;
		this.flagUseable = flagUseable;
	}

	public CarModelLv2(int carModelLv1, String shortName, String fullName,
			Integer orderIndex, String flagUseable) {
		this.carModelLv1 = carModelLv1;
		this.shortName = shortName;
		this.fullName = fullName;
		this.orderIndex = orderIndex;
		this.flagUseable = flagUseable;
	}

    public String getCarModelLv1Name() {
        return carModelLv1Name;
    }

    public void setCarModelLv1Name(String carModelLv1Name) {
        this.carModelLv1Name = carModelLv1Name;
    }

    public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCarModelLv1() {
		return this.carModelLv1;
	}

	public void setCarModelLv1(int carModelLv1) {
		this.carModelLv1 = carModelLv1;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

    public List<CarModelLv3> getCarModelLv3List(){
        return this.carModelLv3List;
    }

    public void setCarModelLv3List(List<CarModelLv3> carModelLv3List){
        this.carModelLv3List = carModelLv3List;
    }



}
