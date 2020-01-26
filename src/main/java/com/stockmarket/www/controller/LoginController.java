package com.stockmarket.www.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired 
	private HttpServletRequest req;
	
	@GetMapping("login-json")
	public String loginJson(@RequestParam("status") String status) {
		System.out.println("로그아웃");
		if(status.equals("logout")) {
			if(req.isRequestedSessionIdValid()) //session 이 유효한 경우 invalidate 처리
				req.getSession().invalidate();	
		}
		
		return "redirect:/main";
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> data) {
		if(data.get("form").equals("회원가입")) {
			String userEmail = data.get("userEmail");
			String pwd = data.get("pwd");
			String nickName = data.get("nickName");
			int vMoney = 1000000; //default 100만원
			
			Member member = new Member(userEmail, nickName, pwd, vMoney);
			loginService.signUpMember(member); 
		}
		
		if(data.get("form").equals("로그인")) {
			String userEmail = data.get("userEmail");
			String pwd = data.get("pwd");

			if(isValidLogInfo(userEmail, pwd)) {
				//session 값을 저장한다
				Member member = loginService.getMember(userEmail);
				req.getSession().setAttribute("id", member.getId());
				req.getSession().setAttribute("nickName", member.getNickName());
			} 
		}
		
		return "redirect:/main";
	}

	private boolean isValidLogInfo(String email, String pwd) {
		if(email == null || email.equals("")) 
			return false;

		System.out.println("member " + loginService.isValidMember(email, pwd));
		if(loginService.isValidMember(email, pwd)) 
			return true;
		else 
			return false;
	}
}
