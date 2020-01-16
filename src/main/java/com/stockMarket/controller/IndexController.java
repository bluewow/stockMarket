package com.stockMarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockMarket.dao.IndexDao;
import com.stockMarket.entity.TestEntity;

@Controller
public class IndexController {
	
	@Autowired
	IndexDao indexDao;
	
//	@ResponseBody
	@GetMapping("/index")
	public String index() {
		TestEntity testEntity = indexDao.get();
		System.out.println(testEntity.toString());
		
		return "index";
	}
}
