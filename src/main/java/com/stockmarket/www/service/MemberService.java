package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.Member;

public interface MemberService {

	List<Member> getMemberList();
	
	Member getMember(int id);
	Member getMemberByNickName(String loginNickname);

	int updateMember(Member memeber);

	

	Boolean isDuplicatedId(String nickname);
	int isDuplicatedNickname(String nickname);
}
