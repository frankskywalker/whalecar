package com.whalecar.domain;

import java.util.List;

public class ShopView extends Shop {

	private static final long serialVersionUID = 6721625095497821469L;
	/**
	 * ShopStock List
	 */
	private List<ShopStockView> shopStockViewList;

    /**
     * carModelLv1List
     * @return
     */
    private List<CarModelLv1View> carModelLv1;

	public List<ShopStockView> getShopStockViewList() {
		return shopStockViewList;
	}

	public void setShopStockViewList(List<ShopStockView> shopStockViewList) {
		this.shopStockViewList = shopStockViewList;
	}

    public List<CarModelLv1View> getCarModelLv1() {
        return carModelLv1;
    }

    public void setCarModelLv1(List<CarModelLv1View> carModelLv1) {
        this.carModelLv1 = carModelLv1;
    }
}
