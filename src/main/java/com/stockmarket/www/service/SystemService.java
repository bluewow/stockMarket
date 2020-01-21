package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.entity.StockDetail;

public interface SystemService {
	/*
	 * TODO
	 * 
	 * 장종료후 주식데이터 갱신
	 * log 시스템
	 * 토, 일요일 및 장쉬는 날 tracking
	 * singleTone 생성하여 path 설정
	 *  - systemService 적용
	 *  - CSV DAO 적용
	*/
	
	/*
	 *  실시가 종목 가격 갱신 및 공유
	*/ 
	void refreshStockPrice();
	
	/*
	 *  하루에 한번 KOSPI.csv KOSDAQ.csv 파일을 갱신한다. 
	*/
	boolean updateMarket(String market);
	
	// 하루에 한번 모든 회원의 당일 자산값을 등록한다.
	int insertRecordAsset();
	
	/*
	 *  해당종목(codeNum)의 모든 정보를  저장한다
	*/
	void setStockDataAll(String codeNum);
	
	/*
	 *  해당종목(codeNum) 의 모든 정보를 불러온다
	*/
	List<StockDetail> getStockDetail(String codeNum);
	
	//TODO
	//상장종목 폐지목록
	//하루에한번 stock 정보 추가
	//추가된 종목 추가
}

