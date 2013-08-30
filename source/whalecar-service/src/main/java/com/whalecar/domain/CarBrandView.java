package com.whalecar.domain;

import java.util.List;

public class CarBrandView extends CarBrand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 106019382671674525L;
	private List<CarModelLv1> carModelLv1List;

	public List<CarModelLv1> getCarModelLv1List() {
		return carModelLv1List;
	}

	public void setCarModelLv1List(List<CarModelLv1> carModelLv1List) {
		this.carModelLv1List = carModelLv1List;
	}

}
