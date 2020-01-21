package com.stockmarket.www.entity;

import java.util.LinkedHashMap;

public class Stock {
	private String codeNum;
	private String name;
	
	//이하 데이터를 얻기위해 CSV 를 참조한다
	private int price;
	private LinkedHashMap<String, Integer> day;
	private LinkedHashMap<String, Integer> month;
	private LinkedHashMap<String, Integer> year;
	
	//TODO
	//PER, PBR, ROE
	//수급
	//자산총계
	//부채
	
	public String getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
