package com.whalecar.domain;

import java.util.List;

public class ShopView extends Shop {
	/**
	 * ShopStock List
	 */
	private List<ShopStockView> shopStockViewList;

	public List<ShopStockView> getShopStockViewList() {
		return shopStockViewList;
	}

	public void setShopStockViewList(List<ShopStockView> shopStockViewList) {
		this.shopStockViewList = shopStockViewList;
	}

}
