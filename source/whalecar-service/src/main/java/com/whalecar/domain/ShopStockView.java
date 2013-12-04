package com.whalecar.domain;

import java.math.BigDecimal;

public class ShopStockView extends ShopStock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5932867829507068205L;
	private String carModelLv3ShortName;
	private String carModelLv3FullName;
	private BigDecimal factoryPrice;
	private String shopName;

	private String carOutsideColorName;
    private String carOutsideColorRgb;

    private String carModelLv2ShortName;
    private String carModelLv2FullName;
    private Integer carModelLv2Id;

    private String carModelLv1Cname;
    private Integer carModelLv1Id;
    private String carBrandCname;

    private String carAdditionsText;


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
        return factoryPrice;
    }

    public void setFactoryPrice(BigDecimal factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public String getCarOutsideColorName() {
		return carOutsideColorName;
	}

	public void setCarOutsideColorName(String carOutsideColorName) {
		this.carOutsideColorName = carOutsideColorName;
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

    public String getCarModelLv2ShortName() {
        return carModelLv2ShortName;
    }

    public void setCarModelLv2ShortName(String carModelLv2ShortName) {
        this.carModelLv2ShortName = carModelLv2ShortName;
    }

    public String getCarModelLv2FullName() {
        return carModelLv2FullName;
    }

    public void setCarModelLv2FullName(String carModelLv2FullName) {
        this.carModelLv2FullName = carModelLv2FullName;
    }

    public String getCarModelLv1Cname() {
        return carModelLv1Cname;
    }

    public void setCarModelLv1Cname(String carModelLv1Cname) {
        this.carModelLv1Cname = carModelLv1Cname;
    }

    public String getCarBrandCname() {
        return carBrandCname;
    }

    public void setCarBrandCname(String carBrandCname) {
        this.carBrandCname = carBrandCname;
    }

    public String getCarOutsideColorRgb() {
        return carOutsideColorRgb;
    }

    public void setCarOutsideColorRgb(String carOutsideColorRgb) {
        this.carOutsideColorRgb = carOutsideColorRgb;
    }

    public Integer getCarModelLv2Id() {
        return carModelLv2Id;
    }

    public void setCarModelLv2Id(Integer carModelLv2Id) {
        this.carModelLv2Id = carModelLv2Id;
    }

    public Integer getCarModelLv1Id() {
        return carModelLv1Id;
    }

    public void setCarModelLv1Id(Integer carModelLv1Id) {
        this.carModelLv1Id = carModelLv1Id;
    }

    public String getCarAdditionsText() {
        return carAdditionsText;
    }

    public void setCarAdditionsText(String carAdditionsText) {
        this.carAdditionsText = carAdditionsText;
    }
}
