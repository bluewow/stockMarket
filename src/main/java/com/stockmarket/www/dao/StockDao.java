package com.stockmarket.www.dao;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.Stock;

@Mapper
public interface StockDao {
	String getStockName(String codeNum);
	String getStockCodeNum(String name);
}
