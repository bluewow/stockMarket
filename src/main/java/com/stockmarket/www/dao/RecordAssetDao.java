package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.RecordAsset;

@Mapper
public interface RecordAssetDao {

	/* 자산 추이 그래프에서 쓰일 자산 기록 목록 */
	List<RecordAsset> getList(int memberId);
	
	/* 하루에 한번씩 자산 기록 등록 */
	int insert(RecordAsset recordAsset);
	/* 탈퇴했을 경우 자산 기록 삭제 */
	int delete(int memberId); 
}
