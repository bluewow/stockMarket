package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CaptureMemoView;
import com.stockmarket.www.entity.CommunityBoard;

public interface CaptureMemoService {
//	캡쳐메모 목록
	List<CaptureMemoView> getList(int id);
	
// 캡쳐메모 내용
	CaptureMemo get(int id);

// 캡쳐메모 수정 삭제
	int update(CaptureMemo captureMemo);
	int delete(int id);
	int insert(CaptureMemoView memoView);

	String captureDataCrawling(String codeNum, int memberId);
}
