package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.Member;

public interface MemberService {

	List<Member> getMemberList();
	
	Member getMember(int id);

	int updateMember(int id, int profileImg, String imgChange);

	int updateMember(int id, String newPwd, String imgChange);

	Boolean isDuplicatedId(String nickname);
}
