package com.stockmarket.www.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.service.AnalysisService;

@Controller
@RequestMapping("/card/trade/")
public class AnalysisController {
	
	@Autowired
	private AnalysisService service;

	@Autowired 
	private HttpServletRequest req;
	
	@ResponseBody
	@GetMapping("capture-json") //capture
	public String captureJson(@RequestParam(defaultValue="005930", name="codeNum") String codeNum) {

		int memberId = (int)req.getSession().getAttribute("id");
		String result = service.captureDataCrawling(codeNum, memberId);
		
		return result;
	}
	
	@ResponseBody
	@GetMapping("analysis-json") //view update
	public String analaysisJson(@RequestParam("codeNum") String codeNum) {
		
		HashMap<Object, Object>map = new HashMap<>();
		Map<String, CurStock> stocks = AppContext.getStockMarket();
		CurStock curStock = null; 
		for(String getCodeNume : stocks.keySet()) {
			if(getCodeNume.equals(codeNum)) {
//			System.out.println(stocks.get(getCodeNume).toString()); //for debugging
				curStock = new CurStock(
						codeNum,
						stocks.get(getCodeNume).getPrice(),
						stocks.get(getCodeNume).getGain(), 
						stocks.get(getCodeNume).getGainPrice(), 
						stocks.get(getCodeNume).getSignMark(), 
						stocks.get(getCodeNume).getPercent());
				break;
			} 
		}
		
		if(curStock == null)
			curStock = new CurStock(codeNum, "[데이터 수집중...]", "보합", "0", "none", "0");
		
//		System.out.println(curStock.toString());  //for debugging
		map.put("name", service.getStockName(codeNum));
		map.put("price", curStock.getPrice());
		map.put("status", curStock.getGain()); // 상승, 보합, 하락
		map.put("gain", curStock.getGainPrice());
		map.put("ratio", curStock.getPercent());
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		return json;
	}
	
	@GetMapping("analysis") 
	public String analysis(HttpServletRequest req) {
		//default operator
		return "card/trade/analysis";
	}
	
}
