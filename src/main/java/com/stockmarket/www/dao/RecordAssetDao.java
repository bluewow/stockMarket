package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.RecordAsset;

public interface RecordAssetDao {
	
	// 자산추이 그래프
	List<RecordAsset> getList(int memberId);
	
	int insert(RecordAsset recordAsset);
	int delete(int memberId);

}
