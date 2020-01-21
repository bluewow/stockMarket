package com.stockmarket.www.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * 
 * 크롤링 데이터 형태
 * CurrentStockInfo [codeNum=002900, price=948, gain=보합, stuatsPrice=0, signMark=0.00, percent=0]
 * CurrentStockInfo [codeNum=003780, price=3,960, gain=하락, stuatsPrice=50, signMark=-, percent=1.25]
 * CurrentStockInfo [codeNum=003520, price=6,320, gain=상승, stuatsPrice=20, signMark=+, percent=0.32]
 *
 * 
*/
public class CurStock {
	private String codeNum;
	private String price;
	private String gain; //상승 or 하락
	private String gainPrice; //
	private String signMark; //+ or -
	private String percent; //%
	private Map<Integer, Integer> sellQuantityMap;
	private Map<Integer, Integer> buyQuantityMap;
	
	public CurStock() {
	}
	
	
	public CurStock(String codeNum, String price, String gain, String gainPrice, String signMark, String percent) {
		super();
		this.codeNum = codeNum;
		this.price = price;
		this.gain = gain;
		this.gainPrice = gainPrice;
		this.signMark = signMark;
		this.percent = percent;
	}

	public CurStock parser(String text, Map<Integer, Integer> sell, Map<Integer, Integer> buy) {
		String[] data = text.split(" ");
		codeNum = data[0];
		price = data[1];
		gain = data[2];
		gainPrice = data[3];
		signMark = data[4];
		percent = data[5];
		sellQuantityMap = sell;
		buyQuantityMap = buy;

		return this;
	}
	
	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGain() {
		return gain;
	}

	public void setGain(String gain) {
		this.gain = gain;
	}

	public String getGainPrice() {
		return gainPrice;
	}

	public void setGainPrice(String gainPrice) {
		this.gainPrice = gainPrice;
	}

	public String getSignMark() {
		return signMark;
	}

	public void setSignMark(String signMark) {
		this.signMark = signMark;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public Map<Integer, Integer> getSellQuantityMap() {
		return sellQuantityMap;
	}
	
	public void setSellQuantityMap(Map<Integer, Integer> quantityMap) {
		this.sellQuantityMap = quantityMap;
	}
	
	public Map<Integer, Integer> getBuyQuantityMap() {
		return buyQuantityMap;
	}
	
	public void setBuyQuantityMap(Map<Integer, Integer> quantityMap) {
		this.buyQuantityMap = quantityMap;
	}
	

	@Override
	public String toString() {
		return "CurStock [codeNum=" + codeNum + ", price=" + price + ", gain=" + gain + ", gainPrice=" + gainPrice
				+ ", signMark=" + signMark + ", percent=" + percent + "]";
	}
	
	



}
