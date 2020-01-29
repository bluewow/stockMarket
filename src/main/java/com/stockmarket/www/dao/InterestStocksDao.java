package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.InterestStocks;

@Mapper
public interface InterestStocksDao {
	
	List<InterestStocks> getInterestStocks(int id);
	List<InterestStocks> getInterestStockList();
	
	int delete(int id, String delStockName);
	int insert(int id, String stockName);
	
}
