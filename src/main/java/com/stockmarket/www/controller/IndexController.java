package com.stockmarket.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.stockmarket.www.service.MainService;

@Controller
public class IndexController {

	@Autowired
	MainService service;
	
	@Autowired 
	private HttpServletRequest res;
	
	@GetMapping("/index")
	public String index() {

		return "redirect:system";
	}

}
