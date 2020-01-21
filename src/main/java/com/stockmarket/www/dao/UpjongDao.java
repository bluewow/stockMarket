package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.Upjong;

public interface UpjongDao {
	String getUpjong(String stockName);
	List<String> getStockNames(String upjong);
	
	int insert(List<Upjong> list);
	int delete();
	

}
