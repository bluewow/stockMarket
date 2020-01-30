package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.HaveView;

public interface AssetDistrService {
	
	// 자산 분포도 그래프
	// getAssetList
	List<HaveView> getHaveStockList(int memberId);	

}
