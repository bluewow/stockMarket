package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.Member;
import com.stockmarket.www.entity.MemberView;

public interface RankingService {
	List<MemberView> getMemberList();
	
	MemberView getMember(int id);
	int getMemberRank(int id);
}
