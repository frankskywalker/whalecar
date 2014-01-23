package com.whalecar.domain;

import java.math.BigDecimal;

public class CarModelLv1View extends CarModelLv1 {

	private static final long serialVersionUID = -2482311618563362459L;

	private String brandCname;

	private String brandEname;

    /**
     * 库存量
     */
    private Integer carNum;

    private BigDecimal factoryPrice;

    private BigDecimal shopPrice;

    private BigDecimal diffPrice;

	public String getBrandCname() {
		return brandCname;
	}

	public void setBrandCname(String brandCname) {
		this.brandCname = brandCname;
	}

	public String getBrandEname() {
		return brandEname;
	}

	public void setBrandEname(String brandEname) {
		this.brandEname = brandEname;
	}

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public BigDecimal getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(BigDecimal factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }


    public BigDecimal getDiffPrice(){
        if(factoryPrice != null && shopPrice != null){
            return this.factoryPrice.subtract(this.shopPrice).multiply(new BigDecimal(10000));
        }
        else{
            return null;
        }
    }
}
