package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.StockDetail;

@Mapper
public interface StockDetailDao {
	List<StockDetail> get(String codeNum);
	
	//DB 를 채우기 위한 insert 함수 Duplicate error 시 update 로 변환한다
	int insert(List<StockDetail> stocks);
	
	//2016년 이전 데이터는 지워버린다
	int deletePreDate();
	
	//상장폐지시 데이터를 지원버린다
	int delete(String codeNum);
	
}
