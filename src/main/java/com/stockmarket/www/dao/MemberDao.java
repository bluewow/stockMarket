package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.stockmarket.www.entity.Member;

@Mapper
public interface MemberDao {
	List<Member> getMemberList();
	List<Member> getRankerList();
	
	Member getMember(int id);
	Member getMemberByEmail(String query);
	Member getMemberByNickName(String loginNickname);
	int getMemberRank(int id);
	int updateMember(Member member);
//	int updateMember(@Param("id") int id, @Param("cardPos") String cardPos);
	int insertMember(Member member);
	
	//프로필 이미지 교체 (imgChange는 더미)
//	int updateMember(int writerId, int profileImg, String imgChange);
//	int updateMember(int id, String newPwd, String pwdChange);
	
	//닉네임 중복확인
	Boolean isDuplicatedId(String nickname);
}
