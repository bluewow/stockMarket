package com.stockmarket.www.entity;

public class HaveStockView extends HaveStock {
	
	private String stockName;
	
	public HaveStockView() {
	}
	
	public HaveStockView(String stockName) {
		this.stockName = stockName;
	}
	
	public HaveStockView(int memberId, String stockCode, int quantity, int sum, String stockName) {
		super(memberId, stockCode, quantity, sum);
		this.stockName = stockName;		
	}
	

	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Override
	public String toString() {
		return "HaveStockView [stockName=" + stockName + ", toString()=" + super.toString() + "]";
	}



}
