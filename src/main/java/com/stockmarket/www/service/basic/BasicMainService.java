package com.stockmarket.www.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.MainService;

@Service
public class BasicMainService implements MainService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member getMember(int id) {
		return memberDao.getMember(id);
	}

	@Override
	public int updateCardPos(int id, String cardPos) {
		Member member = getMember(id);
		cardPos = cardPos.replaceAll("[\\[|\\]|\"]", "");
		member.setCardPos(cardPos);
		
		return memberDao.updateMember(member);
	}	
}
