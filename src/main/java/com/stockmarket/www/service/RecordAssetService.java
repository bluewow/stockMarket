package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.RecordAsset;

public interface RecordAssetService {
	
	List<RecordAsset> getRecordAssetList(int memberId);
	
	int insertRecordAsset(RecordAsset recordAsset);
	int deleteRecordAsset(int memberId);
}
