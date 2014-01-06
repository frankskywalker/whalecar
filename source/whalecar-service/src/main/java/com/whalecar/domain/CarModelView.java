package com.whalecar.domain;

import java.math.BigDecimal;

public class CarModelView implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 765027789411987876L;
	
	/**
	 * carModelLv1 id
	 */
	private Integer carModelLv1;

	/**
	 * 品牌名称
	 */
	private String brandCname;

    /**
     * 汽车品牌图片地址
     */
    private String brandImgPath;

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

    /**
     * 是否被用户收藏
     */
    private String favorite;

    /**
     * 是否为特惠
     */
    private String offprice;

    /**
     * 是否为
     */
    private String hotmodel;

    private String carModelLv2ShortNames;

	public CarModelView() {

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

	public Integer getCarModelLv1() {
		return carModelLv1;
	}

	public void setCarModelLv1(Integer carModelLv1) {
		this.carModelLv1 = carModelLv1;
	}

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getOffprice() {
        return offprice;
    }

    public void setOffprice(String offprice) {
        this.offprice = offprice;
    }

    public String getHotmodel() {
        return hotmodel;
    }

    public void setHotmodel(String hotmodel) {
        this.hotmodel = hotmodel;
    }

    public String getCarModelLv2ShortNames() {
        return carModelLv2ShortNames;
    }

    public void setCarModelLv2ShortNames(String carModelLv2ShortNames) {
        this.carModelLv2ShortNames = carModelLv2ShortNames;
    }

    public String getBrandImgPath() {
        return brandImgPath;
    }

    public void setBrandImgPath(String brandImgPath) {
        this.brandImgPath = brandImgPath;
    }
}
