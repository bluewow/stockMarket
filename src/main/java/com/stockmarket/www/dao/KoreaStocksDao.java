package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.KoreaStocks;

@Mapper
public interface KoreaStocksDao {
	public KoreaStocks get(String codeNum);
	List<KoreaStocks> getList();
	
	int insert(List<KoreaStocks> list);
	int update(String src, String target);
	int delete();
	
	KoreaStocks searchCompany(String compnayName);
}
