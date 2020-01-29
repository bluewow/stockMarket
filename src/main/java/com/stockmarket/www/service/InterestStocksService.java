package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.InterestStocks;
import com.stockmarket.www.entity.InterestView;

public interface InterestStocksService {
	
	List<InterestStocks> getInterestStockList(); 
	List<InterestView> getInterestViewList(int id); 

	int deleteStock(int userid, String delStockName);
}
