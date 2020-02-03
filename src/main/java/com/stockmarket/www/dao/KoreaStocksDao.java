package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.KoreaStocks;

@Mapper
public interface KoreaStocksDao {
	public KoreaStocks get(String codeNum);
	List<KoreaStocks> getList();
	
	//insert 시도후 Duplicate error 시 update 로 변환한다
	int insertDuplicate(List<KoreaStocks> list);
	int update(String src, String target);
	int delete();
	
	
	KoreaStocks searchCompany(String companyName);
}
