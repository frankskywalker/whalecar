package com.whalecar.domain;

// Generated 2013-7-5 22:40:38 by Hibernate Tools 3.4.0.CR1

/**
 * CarBrand generated by hbm2java
 */
public class CarBrand implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1314734087412333109L;
	private Integer id;
	private String cname;
	private String ename;
	private String logoPath;
	private Integer orderIndex;
	private String flagUseable;

	public CarBrand() {
	}

	public CarBrand(String cname, String ename, String logoPath,
			String flagUseable) {
		this.cname = cname;
		this.ename = ename;
		this.logoPath = logoPath;
		this.flagUseable = flagUseable;
	}

	public CarBrand(String cname, String ename, String logoPath,
			Integer orderIndex, String flagUseable) {
		this.cname = cname;
		this.ename = ename;
		this.logoPath = logoPath;
		this.orderIndex = orderIndex;
		this.flagUseable = flagUseable;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getLogoPath() {
		return this.logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
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
