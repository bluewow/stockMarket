package com.stockmarket.www.entity;

public class InterestStocks {
	
	private int memberId;
	private String stockCode;
	
	public InterestStocks() {
		
	}
	
	public InterestStocks(int memberId, String stockCode) {
		
		this.memberId = memberId;
		this.stockCode = stockCode;
	}


	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	@Override
	public String toString() {
		return "InterestStocks [memberId=" + memberId + ", stockCode=" + stockCode + "]";
	}

	
	
	
	
	
}
