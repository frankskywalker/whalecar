package com.whalecar.domain;

public class ShopStockView extends ShopStock{
	
	private String carModelLv3ShortName;
	private String carModelLv3FullName;
	private long FactoryPrice;
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
	public long getFactoryPrice() {
		return FactoryPrice;
	}
	public void setFactoryPrice(long factoryPrice) {
		FactoryPrice = factoryPrice;
	}
}
