package com.stockmarket.www.entity;

public class HaveStock {
	
	private int memberId;
	private String stockCode;
	private int quantity;
	private int sum;
	
	public HaveStock() {
		// TODO Auto-generated constructor stub
	}
	
	public HaveStock(int memberId, String stockCode, int quantity, int sum) {
		super();
		this.memberId = memberId;
		this.stockCode = stockCode;
		this.quantity = quantity;
		this.sum = sum;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantuty) {
		this.quantity = quantuty;
	}
	
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "HaveStock [memberId=" + memberId + ", stockCode=" + stockCode + ", quantity=" + quantity + ", sum=" + sum
				+ "]";
	}
}
