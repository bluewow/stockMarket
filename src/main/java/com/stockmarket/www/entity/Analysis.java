package com.stockmarket.www.entity;

public class Analysis {
	String codeNum;
	String record_date;
	int supply;
	int influence;
	int trend;
	int contents;
	int scale;
	int result;
	
	public Analysis() {
	}
	
	public Analysis(String codeNum, String record_date, 
			int supply, int influence, int trend, 
			int contents, int scale, int result) {
		super();
		this.codeNum = codeNum;
		this.record_date = record_date;
		this.supply = supply;
		this.influence = influence;
		this.trend = trend;
		this.contents = contents;
		this.scale = scale;
		this.result = result;
	}
	public String getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
	public String getRecord_date() {
		return record_date;
	}
	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}
	public int getSupply() {
		return supply;
	}
	public void setSupply(int supply) {
		this.supply = supply;
	}
	public int getInfluence() {
		return influence;
	}
	public void setInfluence(int influence) {
		this.influence = influence;
	}
	public int getTrend() {
		return trend;
	}
	public void setTrend(int trend) {
		this.trend = trend;
	}
	public int getContents() {
		return contents;
	}
	public void setContents(int contents) {
		this.contents = contents;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getResult() {
		return result;
	}
	public void calculateResultValue() {
		result = supply + influence + trend + contents + scale;
	}
	
	@Override
	public String toString() {
		return "Analysis [codeNum=" + codeNum + ", record_date=" + record_date + ", supply=" + supply + ", influence="
				+ influence + ", trend=" + trend + ", contents=" + contents + ", scale=" + scale + ", result=" + result
				+ "]";
	}
	
	
}
