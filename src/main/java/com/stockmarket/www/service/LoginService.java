package com.stockmarket.www.service;

import com.stockmarket.www.entity.Member;

public interface LoginService {
	
//	등록된 회원인지 체크
	boolean isValidMember(String email, String pwd);
	
//	회원등록
	int signUpMember(Member member);
	
//	회원탈퇴 for admin
	int deleteMember();
	
//  get Member by eMail
	Member getMember(String email);
}
