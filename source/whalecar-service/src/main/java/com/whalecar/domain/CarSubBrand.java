package com.whalecar.domain;

// Generated 2013-7-4 14:32:43 by Hibernate Tools 3.4.0.CR1

/**
 * CarSubBrand generated by hbm2java
 */
public class CarSubBrand implements java.io.Serializable {

	private Integer id;
	private int carBrand;
	private String cname;
	private String ename;
	private Integer orderIndex;
	private String flagUseable;

	public CarSubBrand() {
	}

	public CarSubBrand(int carBrand, String cname, String ename,
			String flagUseable) {
		this.carBrand = carBrand;
		this.cname = cname;
		this.ename = ename;
		this.flagUseable = flagUseable;
	}

	public CarSubBrand(int carBrand, String cname, String ename,
			Integer orderIndex, String flagUseable) {
		this.carBrand = carBrand;
		this.cname = cname;
		this.ename = ename;
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

}
