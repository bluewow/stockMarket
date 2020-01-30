package com.stockmarket.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.tags.Param;

import com.google.gson.Gson;
import com.stockmarket.www.entity.HaveView;
import com.stockmarket.www.entity.InterestView;
import com.stockmarket.www.service.HaveStockService;
import com.stockmarket.www.service.InterestStockService;

@Controller
@RequestMapping(value="/card/managestocks", produces="text/plain;charset=UTF-8")
public class ManageStockController {
	
	@Autowired
	HaveStockService haveStockService;

	@Autowired
	InterestStockService interesrStocksService;
	
	@GetMapping("/holding_list")
	public String holdingStock()
	{
		
		return "/card/managestocks/holdingList";
	}
	
	
	@GetMapping("/interest_list")
	public String interestStock() {
		
		return "/card/managestocks/interestList";
	}
	
	
	@ResponseBody
	@GetMapping("/interest_list_json")
	public String InterestStockGetJSON(@SessionAttribute("id") int id) throws IOException {
		
		return updateInterestCurrentPrice(id);
		
	}
	
	
	@ResponseBody
	private String updateInterestCurrentPrice(int userId)throws IOException {
		 Gson gson = new Gson();
		if(interesrStocksService.getInterestViewList(userId).isEmpty()) {
			String json = gson.toJson(-1);
	        return json;
		}
		else {
		List<InterestView> interestlist = new ArrayList<InterestView>();
		interestlist = interesrStocksService.getInterestViewList(userId);
		String interestJson = gson.toJson(interestlist);
		return interestJson;
		}
	
	}
	
	@ResponseBody
	@PostMapping("/interest_list_json")
	public int InterestStockPostJSON(@SessionAttribute("id") int id, @RequestBody String param) throws IOException {
		int result = interesrStocksService.deleteStock(id,param);	
		return result;
	}
	
	@ResponseBody
	@GetMapping("/holding_list_json")
	public String HoldingStockGetJSON(@SessionAttribute("id") int id) throws IOException {

		return updateHoldingCurrentPrice(id);
		
	}
	
	@ResponseBody
	public String updateHoldingCurrentPrice( int userId) throws IOException {
		
		if(haveStockService.getHaveStockViewList(userId).isEmpty()) {
			   
	        Gson gson = new Gson();
			String json = gson.toJson(-1);
			return json;
			}
			else{
				List<HaveView> list = new ArrayList<HaveView>();
				list = haveStockService.getHaveStockViewList(userId);
		        Gson gson = new Gson();
				String json = gson.toJson(list);
				return json;
			}
		
	}
	
	
	@ResponseBody
	@GetMapping("/manageTest")
	public String manageTest() {
		
		List<InterestView> list = interesrStocksService.getInterestViewList(3);
		for(InterestView m : list)
			System.out.println(m.toString());
		
		return "";
	}
	
	
	

}
