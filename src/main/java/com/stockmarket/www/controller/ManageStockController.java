package com.stockmarket.www.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.service.HoldingStocksService;
import com.stockmarket.www.service.InterestViewService;

@Controller
@RequestMapping("/card/managestocks")
public class ManageStockController {
	
	@Autowired
	HoldingStocksService holdingStockService;
	
	@Autowired
	InterestStocksDao interestService;
	
	@Autowired
	InterestViewService interestViewService;
	
	
	@GetMapping("/holding_list")
	public String holdingStock(Model model,HttpServletRequest request)
	{
		return "holdingList";
	}
	
	
	
	

}
