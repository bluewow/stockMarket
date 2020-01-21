package com.stockmarket.www.entity;

public class Company {
//("회사명 : " + line[0] + ", 종목코드 : " + line[2] + ",  웹사이트 : " + line[7] );
	private String companyName;
	private String codeNum;
	private String stockItemName;
	private String website;
	
	public Company() {
		
	}
	
	public Company(String companyName, String codeNum, String stockItemName, String website) {
	
		this.companyName = companyName;
		this.codeNum = codeNum;
		this.stockItemName = stockItemName;
		this.website = website;
	}

	

	public String getcompanyName() {
		return companyName;
	}

	public void setcompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStockItemName() {
		return stockItemName;
	}

	public void setStockItemName(String stockItemName) {
		this.stockItemName = stockItemName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	
	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", codeNum=" + codeNum + ", stockItemName=" + stockItemName
				+ ", website=" + website + "]";
	}

	
}
