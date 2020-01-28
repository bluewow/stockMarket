package com.stockmarket.www.service;

import com.stockmarket.www.entity.Member;

public interface MainService {
	Member getMember(int id);
	int updateCardPos(int id, String cardPos);
}
