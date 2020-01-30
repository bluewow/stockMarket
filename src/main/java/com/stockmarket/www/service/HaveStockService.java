package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.HaveView;

public interface HaveStockService {
	
	List<HaveView> getHaveStockList(int memberId); 

	HaveView getHaveStockView(int memberId, String stockCode);
	
	int updateHaveStock(HaveStock haveStock);
	int insertHaveStock(HaveStock haveStock);
	int deleteHaveStock(int memberId, String stockCode);
}
