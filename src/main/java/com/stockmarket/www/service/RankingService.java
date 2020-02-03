package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.MemberView;

public interface RankingService {
	List<MemberView> getRankingList();
	MemberView getMemberRank(int id);
}
