package com.stockmarket.www.entity;

public class InterestStocks {
	
	private int memberId;
	private String StockCode;
	
	public InterestStocks() {
		
	}
	
	public InterestStocks(int memberId, String stockCode) {
		
		this.memberId = memberId;
		this.StockCode = stockCode;
	}


	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getStockCode() {
		return StockCode;
	}

	public void setStockCode(String stockCode) {
		StockCode = stockCode;
	}

	@Override
	public String toString() {
		return "InterestStocks [memberId=" + memberId + ", StockCode=" + StockCode + "]";
	}

	
	
	
	
	
}
