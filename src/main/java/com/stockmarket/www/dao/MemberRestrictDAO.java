package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.Member;

//회원제재
public interface MemberRestrictDAO {
	List<Member> getMemberList();
	Member getMember(int id);
}
