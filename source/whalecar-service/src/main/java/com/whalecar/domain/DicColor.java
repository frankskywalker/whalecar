package com.whalecar.domain;

// Generated 2013-7-5 22:40:38 by Hibernate Tools 3.4.0.CR1

/**
 * DicColor generated by hbm2java
 */
public class DicColor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5945668528815691878L;
	private Integer id;
	private String colorCname;
	private String colorEname;
	private String colorRgb;
	private String colorImgPath;
	private Integer orderIndex;
	private String flagUseable;

	public DicColor() {
	}

	public DicColor(String colorCname, String colorEname, String flagUseable) {
		this.colorCname = colorCname;
		this.colorEname = colorEname;
		this.flagUseable = flagUseable;
	}

	public DicColor(String colorCname, String colorEname, String colorRgb,
			String colorImgPath, Integer orderIndex, String flagUseable) {
		this.colorCname = colorCname;
		this.colorEname = colorEname;
		this.colorRgb = colorRgb;
		this.colorImgPath = colorImgPath;
		this.orderIndex = orderIndex;
		this.flagUseable = flagUseable;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColorCname() {
		return this.colorCname;
	}

	public void setColorCname(String colorCname) {
		this.colorCname = colorCname;
	}

	public String getColorEname() {
		return this.colorEname;
	}

	public void setColorEname(String colorEname) {
		this.colorEname = colorEname;
	}

	public String getColorRgb() {
		return this.colorRgb;
	}

	public void setColorRgb(String colorRgb) {
		this.colorRgb = colorRgb;
	}

	public String getColorImgPath() {
		return this.colorImgPath;
	}

	public void setColorImgPath(String colorImgPath) {
		this.colorImgPath = colorImgPath;
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
