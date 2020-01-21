package com.stockmarket.www.entity;

import java.util.Date;

public class CaptureMemo {
	private int id;
	private String title;
	private Date regdate;
	private String content;
	private double PER;
	private double PBR;
	private double ROE;
	private double debtRatio;			// 부채 비율 (%)
	private int marketCap;				// 시가 총액(억)
	private double foreignInvestors; 	//외국인 지뷴율 (%)
	private String codeNum;
	private int memberId;
	
	public CaptureMemo() {
	}
	
	// for update
	public CaptureMemo(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public CaptureMemo(String title, Date regdate) {
		this.title = title;
		this.regdate = regdate;
	}
	
	public CaptureMemo(int id, String title, Date regdate) {
		this.id = id;
		this.title = title;
		this.regdate = regdate;
	}

	public CaptureMemo(int id, String title, Date regdate, String content, double PER, double PBR, double ROE,
			double debtRatio, int marketCap, double foreignInvestors) {
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.PER = PER;
		this.PBR = PBR;
		this.ROE = ROE;
		this.debtRatio = debtRatio;
		this.marketCap = marketCap;
		this.foreignInvestors = foreignInvestors;
	}

	// for insert
	public CaptureMemo(String title, String content, double PER, double PBR, double ROE,
			double debtRatio, int marketCap, double foreignInvestors, String codeNum, int memberId) {
		this.title = title;
		this.content = content;
		this.PER = PER;
		this.PBR = PBR;
		this.ROE = ROE;
		this.debtRatio = debtRatio;
		this.marketCap = marketCap;
		this.foreignInvestors = foreignInvestors;
		this.codeNum = codeNum;
		this.memberId = memberId;
	}

	public CaptureMemo(int id, String title, Date regdate, String content, double PER, double PBR,
			double ROE, double debtRatio, int marketCap, String codeNum, int memberId) {
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.PER = PER;
		this.PBR = PBR;
		this.ROE = ROE;
		this.debtRatio = debtRatio;
		this.marketCap = marketCap;
		this.codeNum = codeNum;
		this.memberId = memberId;
	}

	public CaptureMemo(int id, String title, Date regdate, String content, double PER, double PBR, double ROE,
			double debtRatio, int marketCap, double foreignInvestors, String codeNum, int memberId) {
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.PER = PER;
		this.PBR = PBR;
		this.ROE = ROE;
		this.debtRatio = debtRatio;
		this.marketCap = marketCap;
		this.foreignInvestors = foreignInvestors;
		this.codeNum = codeNum;
		this.memberId = memberId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getPER() {
		return PER;
	}

	public void setPER(double PER) {
		this.PER = PER;
	}

	public double getPBR() {
		return PBR;
	}

	public void setPBR(double PBR) {
		this.PBR = PBR;
	}

	public double getROE() {
		return ROE;
	}

	public void setROE(double ROE) {
		this.ROE = ROE;
	}

	public double getDebtRatio() {
		return debtRatio;
	}

	public void setDebtRatio(double debtRatio) {
		this.debtRatio = debtRatio;
	}


	public int getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(int marketCap) {
		this.marketCap = marketCap;
	}

	public double getForeignInvestors() {
		return foreignInvestors;
	}

	public void setForeignInvestors(float foreignInvestors) {
		this.foreignInvestors = foreignInvestors;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "CaptureMemo [id=" + id + ", title=" + title + ", regdate=" + regdate + ", content=" + content + ", PER="
				+ PER + ", PBR=" + PBR + ", ROE=" + ROE + ", debtRatio=" + debtRatio + ", marketCap=" + marketCap
				+ ", foreignInvestors=" + foreignInvestors + ", codeNum=" + codeNum + ", memberId=" + memberId + "]";
	}

	
}
