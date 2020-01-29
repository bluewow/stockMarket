package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.Upjong;

@Mapper
public interface UpjongDao {
	String getUpjong(String stockName);
	List<String> getStockNames(String upjong);
	
	int insert(List<Upjong> list);
	int delete();
	

}
