package com.stockmarket.www.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.LoginService;

@Controller
public class BasicLoginService implements LoginService{
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean isValidMember(String email, String pwd) {
		Member member = memberDao.getMemberByEmail(email);
		if(member != null) { 
			if(member.getPassword().equals(pwd))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public int signUpMember(Member member) {
	int result = memberDao.insertMember(member);
		
		return result;
	}

	@Override
	public int deleteMember() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member getMember(String email) {
		Member member = memberDao.getMemberByEmail(email);
		if(member != null)
			return member; 
		
		return null;
	}

}
