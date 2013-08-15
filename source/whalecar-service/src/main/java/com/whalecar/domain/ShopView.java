package com.whalecar.domain;

import java.util.List;

public class ShopView extends Shop {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6721625095497821469L;
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
