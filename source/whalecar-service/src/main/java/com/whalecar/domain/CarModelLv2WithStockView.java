package com.whalecar.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarModelLv2WithStockView extends CarModelLv2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9172052435075443512L;

	private List<ShopStockView> shopStockList;

	private BigDecimal factoryPriceMin;

	private BigDecimal factoryPriceMax;

	private BigDecimal carPriceMin;

	private BigDecimal carPriceMax;

	private List<String> outsideColorList = new ArrayList<String>();
	private List<String> insideColorList = new ArrayList<String>();
	private List<String> carModelLv3NameList = new ArrayList<String>();
	/**
	 * 库存总数
	 */
	private Integer stockCount;

	public List<ShopStockView> getShopStockList() {
		return shopStockList;
	}

	public void setShopStockList(List<ShopStockView> shopStockList) {
		this.shopStockList = shopStockList;
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

	public BigDecimal getCarPriceMin() {
		return carPriceMin;
	}

	public void setCarPriceMin(BigDecimal carPriceMin) {
		this.carPriceMin = carPriceMin;
	}

	public BigDecimal getCarPriceMax() {
		return carPriceMax;
	}

	public void setCarPriceMax(BigDecimal carPriceMax) {
		this.carPriceMax = carPriceMax;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public List<String> getOutsideColorList() {
		return outsideColorList;
	}

	public List<String> getInsideColorList() {
		return insideColorList;
	}

	public List<String> getCarModelLv3NameList() {
		return carModelLv3NameList;
	}

}
