package com.stockmarket.www.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.TradeCardData;
import com.stockmarket.www.service.TradeService;

@Controller
@RequestMapping("/card/trade/")
public class TradeController {
	
	@Autowired
	TradeService service;
	
	@Autowired
	private HttpServletRequest req;
	
	@ResponseBody
	@GetMapping("tradeUpdate")
	public String tradeUpdate(@RequestParam(defaultValue="005930", name="codeNum") String codeNum) {
		int memberId = (int)req.getSession().getAttribute("id");
		
		Map<String, CurStock> stocks = AppContext.getStockMarket();
		CurStock curStock = null; 
		TradeCardData data = null;
		
		//크롤링 데이터가 유효한 경우 curStock 을 set 한다
		for(String getCodeNume : stocks.keySet()) {
			if(getCodeNume.equals(codeNum)) {
				curStock = new CurStock(); 
				curStock.setSellQuantityMap(stocks.get(getCodeNume).getSellQuantityMap());
				curStock.setBuyQuantityMap(stocks.get(getCodeNume).getBuyQuantityMap());
				break;
			}
		}

		//크롤링 데이터가 없는 경우, 가상머니와 보유수량만 반환한다
		if(curStock == null) {
			data = new TradeCardData();
			data.setvMoney(service.getAssets(memberId));
			data.setQuantity(service.getQty(memberId, codeNum));
		}
			
		//차트데이터 매도 잔량,매수 잔량 나누어 반환한다
		if(curStock != null) {
			data = new TradeCardData(
					curStock.getSellQuantityMap().size(), curStock.getBuyQuantityMap().size());
			data.setvMoney(service.getAssets(memberId));
			data.setQuantity(service.getQty(memberId, codeNum));
			int i = 0;
			for(Integer sell : curStock.getSellQuantityMap().keySet()) {
				data.setSellPrice(i, sell);
				data.setSellQuantity(i, curStock.getSellQuantityMap().get(sell));
				i++;
			}
			i = 0;
			for(Integer buy : curStock.getBuyQuantityMap().keySet()) {
				data.setBuyPrice(i, buy);
				data.setBuyQuantity(i, curStock.getBuyQuantityMap().get(buy));
				i++;
			}
//				System.out.println(data.toString());
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(data);
		return json;
	}
	
	@ResponseBody
	@GetMapping({"buy","sell"})
	public String buy(@RequestParam Map<String, String> data) {
		int memberId = (int)req.getSession().getAttribute("id");
		String qty = data.get("qty");
		String codeNum = data.get("codeNum");
		String price = data.get("price");
		
		if(service.checkHaveStock(memberId, codeNum) == false)
			service.addHaveStock(memberId, codeNum);	//DB 추가
		
		service.trade(memberId, codeNum, Integer.parseInt(qty), Integer.parseInt(price));
		
		if(service.checkZeroHaveStock(memberId, codeNum)) 
			service.delHaveStock(memberId, codeNum);	//DB 삭제
		
		HashMap<Object, Object>map = new HashMap<>();
		
		map.put("qty", service.getQty(memberId, codeNum));
		map.put("vMoney", (int) service.getAssets(memberId));
		Gson gson = new Gson();
		String json = gson.toJson(map);
		return json;
	}

	@GetMapping("trade")
	public String trade() {
		//default operator
		return "card/trade/trading";
	}
	
}
