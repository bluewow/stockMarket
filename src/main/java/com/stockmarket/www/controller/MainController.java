package com.stockmarket.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.MainService;

@Controller
public class MainController {

	@Autowired
	MainService service;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}

	@ResponseBody
	@GetMapping("/main-json")
	public String mainJsonGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		
		int id = -1;
		
		if(tempId != null)
			id = (Integer)tempId;
		
		Member member = service.getMember(id);
		
		Gson gson = new Gson();
		String json;
		if(id != -1) {
			json = gson.toJson(member.getCardPos());
		} else {
			String defultPos = "1,2,3,4,5,6,0,0,7";
			json = gson.toJson(defultPos);
		}
                
		return json;
	}
	
	@ResponseBody
	@PostMapping("/main-json")
	public int mainJsonPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		String cardPos = request.getParameter("cardPos");
		
		int id = -1;
		
		if(tempId != null)
			id = (Integer)tempId;
						
		int result = service.updateCardPos(id, cardPos);
		         
		return result;
	}
}
