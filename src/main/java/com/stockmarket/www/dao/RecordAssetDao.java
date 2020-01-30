package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.RecordAsset;

@Mapper
public interface RecordAssetDao {
	
	// 자산추이 그래프
	List<RecordAsset> getList(int memberId);
	
	int insert(RecordAsset recordAsset);
	int delete(int memberId);

}
