package com.stockmarket.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.Member;

@Controller
public class ProfileController {

	@Autowired
	private MemberDao memberDao;
	
	@ResponseBody
	@PostMapping("member-profile")
	public int ProfileImg(@RequestParam String loginNickname) {
		Member member = memberDao.getMemberByNickName(loginNickname);
		int result = member.getProfileImg();
		return result;
	}
	
	
}
