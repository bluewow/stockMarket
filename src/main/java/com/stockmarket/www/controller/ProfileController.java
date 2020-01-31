package com.stockmarket.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.MemberService;

@Controller
public class ProfileController {

	
	@Autowired
	private MemberService service;
	
	@ResponseBody
	@PostMapping("member_profile")
	public int ProfileImg(@RequestParam String loginNickname) {
		Member member = service.getMemberByNickName(loginNickname);
		int result = member.getProfileImg();
		return result;
	}
	
	@ResponseBody
	@PostMapping("member_profile_update")
	public int ProfileImgUpdate(@SessionAttribute("id") int id, @RequestParam int profileImg) {
		Member member = service.getMember(id);
		member.setProfileImg(profileImg);
		int result = service.updateMember(member);
		System.out.println(result);
		return result;
	}
	
	
}
