package com.stockmarket.www.service;


import java.util.List;

import com.stockmarket.www.entity.InterestStocks;
import com.stockmarket.www.entity.KoreaStocks;

public interface CompanyService {
	/*
	 * 게시판 즐겨찾기 추가 삭제
	 * 
	 */
	List<InterestStocks> getInterestStocks(int memberId);
	int insertInterest(int memberId, String StockCode);
	int deleteInterest(int memberId, String StockCode);
	
	KoreaStocks searchCompany(String search);
	
	
	/*
	 * search 알고리즘을 사용하여 검색어와 관련된 회사목록을 추출한다
	 * input
	 * 	search : 검색어
	 * 
	 * 문제점
	 * - 1차 필터에서 CJ대한통운 과 같은 단어는 CJ, CJ대한통운으로 결과같이 나온다 
	 * - 조국과 같은 "테마주"는 업종에서 필터링 되지 않는다.
	 */
	public List<String> searchCompanyNames(String search);
	
	
}
