package com.whalecar.domain;

import java.math.BigDecimal;

public class ShopStockView extends ShopStock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5932867829507068205L;
	private String carModelLv3ShortName;
	private String carModelLv3FullName;
	private BigDecimal FactoryPrice;
	private String shopName;

	private String carOutsideColorName;
	private String carInsideColorName;

	/**
	 * 驱动类型
	 */
	private String driveType;
	/**
	 * 变速箱类型
	 */
	private String gearType;

	public String getCarModelLv3ShortName() {
		return carModelLv3ShortName;
	}

	public void setCarModelLv3ShortName(String carModelLv3ShortName) {
		this.carModelLv3ShortName = carModelLv3ShortName;
	}

	public String getCarModelLv3FullName() {
		return carModelLv3FullName;
	}

	public void setCarModelLv3FullName(String carModelLv3FullName) {
		this.carModelLv3FullName = carModelLv3FullName;
	}

	public BigDecimal getFactoryPrice() {
		return FactoryPrice;
	}

	public void setFactoryPrice(BigDecimal factoryPrice) {
		FactoryPrice = factoryPrice;
	}

	public String getCarOutsideColorName() {
		return carOutsideColorName;
	}

	public void setCarOutsideColorName(String carOutsideColorName) {
		this.carOutsideColorName = carOutsideColorName;
	}

	public String getCarInsideColorName() {
		return carInsideColorName;
	}

	public void setCarInsideColorName(String carInsideColorName) {
		this.carInsideColorName = carInsideColorName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public String getGearType() {
		return gearType;
	}

	public void setGearType(String gearType) {
		this.gearType = gearType;
	}

}
