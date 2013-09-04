package com.whalecar.domain;

import java.io.Serializable;
import java.math.BigDecimal;

// Generated 2013-7-5 22:40:38 by Hibernate Tools 3.4.0.CR1

/**
 * ShopStock generated by hbm2java
 */
public class ShopStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5492528263101538200L;
	private Integer id;
	private int shop;
	private int carModelLv3;
	private int carOutsideColor;
	private int carInsideColor;
	private String carAddition;
	private BigDecimal carPrice;
	private int carOnOrderNum;
	private int carOnHandNum;

	public ShopStock() {
	}

	public ShopStock(int shop, int carModelLv3, int carOutsideColor,
			int carInsideColor, BigDecimal carPrice, int carOnOrderNum,
			int carOnHandNum) {
		this.shop = shop;
		this.carModelLv3 = carModelLv3;
		this.carOutsideColor = carOutsideColor;
		this.carInsideColor = carInsideColor;
		this.carPrice = carPrice;
		this.carOnOrderNum = carOnOrderNum;
		this.carOnHandNum = carOnHandNum;
	}

	public ShopStock(int shop, int carModelLv3, int carOutsideColor,
			int carInsideColor, String carAddition, BigDecimal carPrice,
			int carOnOrderNum, int carOnHandNum) {
		this.shop = shop;
		this.carModelLv3 = carModelLv3;
		this.carOutsideColor = carOutsideColor;
		this.carInsideColor = carInsideColor;
		this.carAddition = carAddition;
		this.carPrice = carPrice;
		this.carOnOrderNum = carOnOrderNum;
		this.carOnHandNum = carOnHandNum;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getShop() {
		return this.shop;
	}

	public void setShop(int shop) {
		this.shop = shop;
	}

	public int getCarModelLv3() {
		return this.carModelLv3;
	}

	public void setCarModelLv3(int carModelLv3) {
		this.carModelLv3 = carModelLv3;
	}

	public int getCarOutsideColor() {
		return this.carOutsideColor;
	}

	public void setCarOutsideColor(int carOutsideColor) {
		this.carOutsideColor = carOutsideColor;
	}

	public int getCarInsideColor() {
		return this.carInsideColor;
	}

	public void setCarInsideColor(int carInsideColor) {
		this.carInsideColor = carInsideColor;
	}

	public String getCarAddition() {
		return this.carAddition;
	}

	public void setCarAddition(String carAddition) {
		this.carAddition = carAddition;
	}

	public BigDecimal getCarPrice() {
		return this.carPrice;
	}

	public void setCarPrice(BigDecimal carPrice) {
		this.carPrice = carPrice;
	}

	public int getCarOnOrderNum() {
		return this.carOnOrderNum;
	}

	public void setCarOnOrderNum(int carOnOrderNum) {
		this.carOnOrderNum = carOnOrderNum;
	}

	public int getCarOnHandNum() {
		return this.carOnHandNum;
	}

	public void setCarOnHandNum(int carOnHandNum) {
		this.carOnHandNum = carOnHandNum;
	}

}
