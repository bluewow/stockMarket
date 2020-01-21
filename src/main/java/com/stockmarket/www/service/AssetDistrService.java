package com.stockmarket.www.service;

import java.util.List;
import java.util.Map;

import com.stockmarket.www.entity.HaveStockView;

public interface AssetDistrService {
	
	// 자산 분포도 그래프
	// getAssetList
	List<HaveStockView> getHaveStockList(int memberId);	

}
