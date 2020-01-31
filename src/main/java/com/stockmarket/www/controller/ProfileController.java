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
	@PostMapping("member_profile_update_ing")
	public int ProfileImgUpdate(@SessionAttribute("id") int id, @RequestParam int profileImg) {
		Member member = service.getMember(id);
		member.setProfileImg(profileImg);
		int result = service.updateMember(member);
		System.out.println(result);
		return result;
	}
	
	@ResponseBody
	@PostMapping("member_profile_update_pwd")
	public int ProfilePwdUpdate(@SessionAttribute("id") int id, 
			@RequestParam String currentPwd, @RequestParam String newPwd) {
		Member member = service.getMember(id);
		int result = 0;
		
		if(!member.getPassword().equals(currentPwd)) {
			result = 2;
		} else if(member.getPassword().equals(newPwd)) {
			result = 3;
		} else if (member.getPassword().equals(currentPwd)) {
			member.setPassword(newPwd);
			result = service.updateMember(member);
			
		}  
		return result;
	}
	
	@ResponseBody
	@PostMapping("member_nickname_validate")
	public int ProfileValidate(@RequestParam String nickname) {
		return service.isDuplicatedNickname(nickname);
	}
	
	@ResponseBody
	@PostMapping("member_email_validate")
	public int EmailValidate(@RequestParam String email) {
		return service.isDuplicatedEmail(email);
	}
	
	
}
