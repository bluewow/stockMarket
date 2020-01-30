package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.HaveView;

public interface HaveStockService {
	
	List<HaveView> getHaveStockViewList(int memberId); 

	HaveView getHaveStockView(int memberId, String stockCode); 
}
