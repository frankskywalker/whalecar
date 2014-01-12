package com.whalecar.domain;

public class CarModelLv1View extends CarModelLv1 {

	private static final long serialVersionUID = -2482311618563362459L;

	private String brandCname;

	private String brandEname;

    /**
     * 库存量
     */
    private Integer carNum;

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
}
