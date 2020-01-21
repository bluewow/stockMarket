package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.RecordAsset;

public interface AssetTrendService {
	
	// 현 보유 자산
	long getAssetPresent(int memberId);
	
	// 자산추이 그래프
	List<RecordAsset> getRecordAsset(int memberId);

}
