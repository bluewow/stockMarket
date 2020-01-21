package com.stockmarket.www.entity;
//koreaStocks = 코스피+ 코스닥

public class KoreaStocks {
	
	// 회사명
	private String companyName;
	// 종목코드
	private String stockCode;
	// 업종
	private String sectors;
	// 주요 제품
	private String mainProduct;
	// 상장일
	private String stockedDay;
	// 결산월
	private String settlementMonth;
	// 대표자명 
	private String representativeName;
	// 웹사이트
	private String website;
	// 지역
	private String location;
	
	public KoreaStocks() {
		
	}
	
	public KoreaStocks(String companyName, String stockCode, String sectors, String mainProduct, String stockedDay,
			String settlementMonth, String representativeName, String website, String location) {
		
		this.companyName = companyName;
		this.stockCode = stockCode;
		this.sectors = sectors;
		this.mainProduct = mainProduct;
		this.stockedDay = stockedDay;
		this.settlementMonth = settlementMonth;
		this.representativeName = representativeName;
		this.website = website;
		this.location = location;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getSectors() {
		return sectors;
	}
	public void setSectors(String sectors) {
		this.sectors = sectors;
	}
	public String getMainProduct() {
		return mainProduct;
	}
	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}
	public String getStockedDay() {
		return stockedDay;
	}
	public void setStockedDay(String stockedDay) {
		this.stockedDay = stockedDay;
	}
	public String getSettlementMonth() {
		return settlementMonth;
	}
	public void setSettlementMonth(String settlementMonth) {
		this.settlementMonth = settlementMonth;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "koreaStocks [companyName=" + companyName + ", stockCode=" + stockCode + ", sectors=" + sectors
				+ ", mainProduct=" + mainProduct + ", stockedDay=" + stockedDay + ", settlementMonth="
				+ settlementMonth + ", representativeName=" + representativeName + ", website=" + website
				+ ", location=" + location + "]";
	}
	
	
}
