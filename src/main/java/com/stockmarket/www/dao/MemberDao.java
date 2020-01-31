package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.Member;
import com.stockmarket.www.entity.MemberView;

@Mapper
public interface MemberDao {
	List<Member> getMemberList();
	Member getMember(int id);
	Member getMemberByEmail(String query);
	Member getMemberByNickName(String loginNickname);
	int updateMember(Member member);
	int insertMember(Member member);
	
	List<MemberView> getRankerList();
	MemberView getMemberRank(int id);
	
	//닉네임 중복확인
	Boolean isDuplicatedId(String nickname);
}
