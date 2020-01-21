package com.stockmarket.www.entity;

import java.util.Arrays;

public class TradeCardData {
	long vMoney;	//자산상황
	int qty; //수량
	int[] sellPrice;
	int[] sellQty;
	int[] buyPrice;
	int[] buyQty;
	
	public TradeCardData() {
	}
	
	public TradeCardData(int sellCnt, int butCnt) {
		sellPrice = new int[sellCnt];
		sellQty = new int[sellCnt];
		buyPrice = new int[butCnt];;
		buyQty = new int[butCnt];;
	}
	
	public long getvMoney() {
		return vMoney;
	}
	public void setvMoney(long vMoney) {
		this.vMoney = vMoney;
	}
	public int getQuantity() {
		return qty;
	}
	public void setQuantity(int qty) {
		this.qty = qty;
	}
	public int[] getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int i, int sellPrice) {
		this.sellPrice[i] =sellPrice;
	}
	public int[] getSellQuantity() {
		return sellQty;
	}
	public void setSellQuantity(int i, int sellQuantity) {
		this.sellQty[i] = sellQuantity;
	}
	public int[] getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int i, int buyPrice) {
		this.buyPrice[i] = buyPrice;
	}
	public int[] getBuyQuantity() {
		return buyQty;
	}
	public void setBuyQuantity(int i, int buyQuantity) {
		this.buyQty[i] = buyQuantity;
	}

	@Override
	public String toString() {
		return "TradeCardData [vMoney=" + vMoney + ", quantity=" + qty + ", sellPrice="
				+ Arrays.toString(sellPrice) + ", sellQuantity=" + Arrays.toString(sellQty) + ", buyPrice="
				+ Arrays.toString(buyPrice) + ", buyQuantity=" + Arrays.toString(buyQty) + "]";
	}
	
	
}
