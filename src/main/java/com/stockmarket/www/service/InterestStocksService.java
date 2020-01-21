package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.InterestStocks;

public interface InterestStocksService {
	
	List<InterestStocks> getInterestStockList(); 

	int deleteStock(int userid, String delStockName);
}
