package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.Member;
import com.stockmarket.www.entity.Stock;
import com.stockmarket.www.entity.StockDetail;

public interface StockDetailDao {
	List<StockDetail> get(String codeNum);
	
	//DB 를 채우기 위한 insert 함수
	int insert(StockDetail[] stocks);
	//daily insert 함수
	int insertDaily(StockDetail stock);
	
	//2016년 이전 데이터는 지워버린다
	int deletePreDate();
	//상장폐지시 데이터를 지원버린다
	int delete(String codeNum);
	
}
