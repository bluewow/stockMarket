package com.stockmarket.www.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.MemberService;

@Service
public class BasicMemberService implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public Member getMember(int id) {
		return memberDao.getMember(id);
	}

	@Override
	public Boolean isDuplicatedId(String nickname) {
		return memberDao.isDuplicatedId(nickname);
	}

	@Override
	public Member getMemberByNickName(String loginNickname) {
		return memberDao.getMemberByNickName(loginNickname);
	}

	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}

	@Override
	public int isDuplicatedNickname(String nickname) {
		if(memberDao.getMemberByNickName(nickname)==null)
			return 2;
		else return 1;
	}

	@Override
	public int isDuplicatedEmail(String email) {
		if(memberDao.getMemberByEmail(email)==null)
			return 2;
		else return 1;
	}
}
