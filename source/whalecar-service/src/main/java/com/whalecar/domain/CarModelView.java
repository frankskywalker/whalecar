package com.whalecar.domain;

import java.math.BigDecimal;

public class CarModelView  implements java.io.Serializable{
	
	/**
	 * 品牌名称
	 */
	private String brandCname;
	
	/**
	 * 型号名称
	 */
	private String cname;
	
	/**
	 * 图片相对路径
	 */
	private String imgPath;
	
	/**
	 * 官方指导价格最低价
	 */
	private BigDecimal factoryPriceMin;
	
	/**
	 * 官方指导价格最高价
	 */
	private BigDecimal factoryPriceMax;
	
	/**
	 * 4s店最高报价
	 */
	private String shopPriceMin;
	
	/**
	 * 4s店最低报价
	 */
	private String shopPriceMax;
	
	public CarModelView(){
		
	}
	
	public String getBrandCname() {
		return brandCname;
	}

	public void setBrandCname(String brandCname) {
		this.brandCname = brandCname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public BigDecimal getFactoryPriceMin() {
		return factoryPriceMin;
	}

	public void setFactoryPriceMin(BigDecimal factoryPriceMin) {
		this.factoryPriceMin = factoryPriceMin;
	}

	public BigDecimal getFactoryPriceMax() {
		return factoryPriceMax;
	}

	public void setFactoryPriceMax(BigDecimal factoryPriceMax) {
		this.factoryPriceMax = factoryPriceMax;
	}

	public String getShopPriceMin() {
		return shopPriceMin;
	}

	public void setShopPriceMin(String shopPriceMin) {
		this.shopPriceMin = shopPriceMin;
	}

	public String getShopPriceMax() {
		return shopPriceMax;
	}

	public void setShopPriceMax(String shopPriceMax) {
		this.shopPriceMax = shopPriceMax;
	}
}
