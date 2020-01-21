package com.stockmarket.www.dao;

import com.stockmarket.www.entity.Stock;

public interface StockDao {
	String getStockName(String codeNum);
	String getStockCodeNum(String name);
}
