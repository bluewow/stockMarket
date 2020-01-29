package com.stockmarket.www.entity;

public class InterestStockView {
	int id;
	String stockName;
	String stockCode;
	 
	public InterestStockView() {
		
	}
	
	public InterestStockView(int id, String stockName, String stockCode) {
		super();
		this.id = id;
		this.stockName = stockName;
		this.stockCode = stockCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	@Override
	public String toString() {
		return "InterestStockView [id=" + id + ", stockName=" + stockName + ", stockCode=" + stockCode + "]";
	}
	
	
	
	
	

}
