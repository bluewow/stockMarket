package com.stockmarket.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockmarket.www.dao.DemoDao;
import com.stockmarket.www.entity.Demo;

@Controller
public class DemoController {
	
	@Autowired
	DemoDao demoDao;
	
//	@ResponseBody
	@GetMapping("/demo")
	public String demo(Model model) {
		Demo demo = demoDao.get();
		model.addAttribute("demo", demo);
		return "demo";
	}
}
