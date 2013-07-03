package com.whalecar.domain;

// Generated 2013-7-2 15:12:51 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * UserOrder generated by hbm2java
 */
public class UserOrder implements java.io.Serializable {

	private Integer id;
	private String orderSn;
	private String orderTitle;
	private String orderState;
	private Date orderCreateDate;
	private Date orderPayDate;
	private Date orderConfirmDate;
	private Date orderCompleteDate;
	private Date orderCancelDate;
	private long orderPrice;
	private int shopStock;
	private int shop;

	public UserOrder() {
	}

	public UserOrder(String orderSn, String orderTitle, String orderState,
			Date orderCreateDate, Date orderPayDate, Date orderConfirmDate,
			Date orderCompleteDate, Date orderCancelDate, long orderPrice,
			int shopStock, int shop) {
		this.orderSn = orderSn;
		this.orderTitle = orderTitle;
		this.orderState = orderState;
		this.orderCreateDate = orderCreateDate;
		this.orderPayDate = orderPayDate;
		this.orderConfirmDate = orderConfirmDate;
		this.orderCompleteDate = orderCompleteDate;
		this.orderCancelDate = orderCancelDate;
		this.orderPrice = orderPrice;
		this.shopStock = shopStock;
		this.shop = shop;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderSn() {
		return this.orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getOrderTitle() {
		return this.orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Date getOrderCreateDate() {
		return this.orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public Date getOrderPayDate() {
		return this.orderPayDate;
	}

	public void setOrderPayDate(Date orderPayDate) {
		this.orderPayDate = orderPayDate;
	}

	public Date getOrderConfirmDate() {
		return this.orderConfirmDate;
	}

	public void setOrderConfirmDate(Date orderConfirmDate) {
		this.orderConfirmDate = orderConfirmDate;
	}

	public Date getOrderCompleteDate() {
		return this.orderCompleteDate;
	}

	public void setOrderCompleteDate(Date orderCompleteDate) {
		this.orderCompleteDate = orderCompleteDate;
	}

	public Date getOrderCancelDate() {
		return this.orderCancelDate;
	}

	public void setOrderCancelDate(Date orderCancelDate) {
		this.orderCancelDate = orderCancelDate;
	}

	public long getOrderPrice() {
		return this.orderPrice;
	}

	public void setOrderPrice(long orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getShopStock() {
		return this.shopStock;
	}

	public void setShopStock(int shopStock) {
		this.shopStock = shopStock;
	}

	public int getShop() {
		return this.shop;
	}

	public void setShop(int shop) {
		this.shop = shop;
	}

}
