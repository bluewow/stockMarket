package com.stockmarket.www.service.basic;

import java.util.List;

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
	public List<Member> getMemberList() {
		return memberDao.getRankerList();
	}

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
}
