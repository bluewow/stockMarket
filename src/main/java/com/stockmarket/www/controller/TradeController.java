package com.stockmarket.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/card/trade/")
public class TradeController {
	
	@ResponseBody
	@GetMapping("tradeUpdate")
	public String tradeUpdate() {
//		System.out.println("tradeUpdate");
		return null;
	}
	
	@ResponseBody
	@GetMapping("buy")
	public String buy() {
		
		return null;
	}
	
	@ResponseBody
	@GetMapping("sell")
	public String sell() {
		
		return null;
	}
	
	@GetMapping("trade")
	public String trade() {
		//default operator
		return "card/trade/trading";
	}
}
