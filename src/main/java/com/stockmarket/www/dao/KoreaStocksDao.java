package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.KoreaStocks;

public interface KoreaStocksDao {
	public KoreaStocks get(String codeNum);
	List<KoreaStocks> getList();
	
	int insert(List<KoreaStocks> list);
	int update(String src, String target);
	int delete();
	
	KoreaStocks searchCompany(String compnayName);
}
