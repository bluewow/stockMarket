package com.stockmarket.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.dao.InterestViewDao;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.entity.InterestStockView;
import com.stockmarket.www.entity.InterestView;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.HoldingStocksService;
import com.stockmarket.www.service.InterestStockService;

@Controller
@RequestMapping("/card/managestocks")
public class ManageStockController {
	
//	@Autowired
//	HoldingStocksService holdingStockService;

//	@Autowired
//	InterestViewDao stockDao;
	
	@Autowired
	InterestStockService interesrStocksService;
	
	@GetMapping("/holding_list")
	public String holdingStock()
	{
		
		return "holdingList";
	}
	
	
	@GetMapping("/interest_list")
	public String interestStock() {
		
		return "/card/managestocks/interestList";
	}
	
	
	
	@GetMapping("/interest_list_json")
	public void InterestStockGetJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("id");
		
		updateInterestCurrentPrice(request,response,userId);
	    
	}
	
	private void updateInterestCurrentPrice(HttpServletRequest request,HttpServletResponse response , int userId)throws IOException {
		
		if(interesrStocksService.getInterestViewList(userId).isEmpty()) {
		    Gson gson = new Gson();
			String json = gson.toJson(-1);
	        PrintWriter out = response.getWriter();
	        out.write(json);
		}
		else {
		List<InterestView> interestlist = new ArrayList<InterestView>();
		interestlist = interesrStocksService.getInterestViewList(userId);
        Gson interestGson = new Gson();
		String interestJson = interestGson.toJson(interestlist);
        PrintWriter out = response.getWriter();
		out.write(interestJson);
		}
		
	}
	
	@PostMapping("/interest_list_json")
	public void InterestStockPostJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        HttpSession session = request.getSession();
        
		int userId = (int)session.getAttribute("id");
		String delStockName = request.getParameter("delStockName");

		int result = interesrStocksService.deleteStock(userId,delStockName);	
        PrintWriter out = response.getWriter();
		out.write(result);
	}
	
	
//	@GetMapping("/holding_list_json")
//	public void HoldingStockGetJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		HttpSession session = request.getSession();
//		int userId = (int)session.getAttribute("id");
//		
//		updateHoldingCurrentPrice(request,response,userId);
//	}
//	
//	public void updateHoldingCurrentPrice(HttpServletRequest request,HttpServletResponse response , int userId) throws IOException {
//		
//		if(holdingStockService.getInterestHoldingList(userId).isEmpty()) {
//			   
//	        Gson gson = new Gson();
//			String json = gson.toJson(-1);
//	        PrintWriter out = response.getWriter();
//			out.write(json);
//			}
//			else{
//				List<HaveStockView> list = new ArrayList<HaveStockView>();
//				list = holdingStockService.getInterestHoldingList(userId);
//		        Gson gson = new Gson();
//				String json = gson.toJson(list);
//		        PrintWriter out = response.getWriter();
//				out.write(json);
//			}
//		
//	}
	
	
//	@ResponseBody
//	@GetMapping("/manageTest")
//	public String manageTest() {
//		
//		List<InterestStockView> list = stockDao.getInterestStockList(3);
//		for(InterestStockView m : list)
//			System.out.println(m.toString());
//		
//		return "";
//	}
	
	
	

}
