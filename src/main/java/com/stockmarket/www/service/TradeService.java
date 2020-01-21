package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.StockDetail;

//Quantity -> Qty
public interface TradeService {
	/* 자산정보를 가져온다	 */
	long getAssets(int id);
	
	/* 해당종목의 자산을 가져온다 */
	int getStockAssets(int id, String stockId);
	
	/* 종목수량을 가져한다 */
	int getQty(int id, String stockId);

	/* 종목수량  및 sum 값을 변경한다 */
	void setQty(int id, String stockId, int qty, int curPrice);
	
	/* 매수시 가상머니 체크 
	 * return 
	 * 	0:정상 
	 * 	1:vmoney 없음	 */
	int checkVmoney(int id, int qty, int curStockPrice);
	
	/* 주식의 소유여부를 체크하여 없으면 addHaveStock 을 실행한다 */
	boolean checkHaveStock(int id, String codeNum);
	
	/* 주식 - Zero 수량을 체크한다 */
	boolean checkZeroHaveStock(int id, String codeNum);
	/* 주식 - Minus 수량을 체크한다 */
	boolean checkMinusHaveStock(int id, String codeNum, int qty);
	
	/* HaveStock DB 에 데이터 추가*/
	void addHaveStock(int id, String codeNum);
	/* HaveStock DB 에 데이터 삭제*/
	void delHaveStock(int memberId, String codeNum);
	
	/* 매수/매도 실행 */
	void trade(int id, String codeNum, int qty, int curStockPrice);
	
	/* 일봉 데이터를 가져온다 */
	List<StockDetail> getDailyPrice(String codeNum);

	/* 회사 이름을 가져온다 */
	String getCompanyName(String codeNum);

	
}
