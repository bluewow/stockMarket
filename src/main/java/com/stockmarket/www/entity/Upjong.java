package com.stockmarket.www.entity;

public class Upjong {
	
	private String upjong;
	private String stockName;
	
	public Upjong(String upjong, String stockName) {
		
		this.upjong = upjong;
		this.stockName = stockName;
	}
	
	public String getUpjong() {
		return upjong;
	}
	
	public void setUpjong(String upjong) {
		this.upjong = upjong;
	}
	
	public String getStockName() {
		return stockName;
	}
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	@Override
	public String toString() {
		return "Upjong [upjong=" + upjong + ", stockName=" + stockName + "]";
	}
	
	
}
