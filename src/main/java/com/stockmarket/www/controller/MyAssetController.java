package com.stockmarket.www.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.stockmarket.www.entity.HaveView;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.service.AssetDistrService;
import com.stockmarket.www.service.AssetTrendService;

// annotation인 @Controller를 붙여서 해당 클래스가 Controller라는 것을 spring에게 알려준다.
@Controller
public class MyAssetController {

	@Autowired
	private AssetTrendService trendService;
	@Autowired
	private AssetDistrService distrService;

	@GetMapping("/card/asset/myAsset") // View의 요청 경로를 GetMapping을 통해 /demo로 설정한다.
	public String myAsset(Model model, @SessionAttribute("id") int userId) {
		
//		userId = 3;
		/* 현재의 보유 자산 */
		long assetPresent = trendService.getAssetPresent(userId);
		/* 전날까지 또는 당일 오후 5시 이후의 자산 기록이 포함된 리스트 */
		List<RecordAsset> trendList = trendService.getRecordAssetList(userId);
		/* 자산 비율 리스트 */
		List<HaveView> distrList = distrService.getHaveStockList(userId);

		model.addAttribute("assetPresent", assetPresent);
		model.addAttribute("trendList", trendList);
		model.addAttribute("distrList", distrList);
		
		System.out.println("나 호출하냐 ??");

		return "card/asset/myAsset";
	}
	
	@ResponseBody
	@GetMapping("/card/asset/myAsset-json")
	public String myAssetJson(Model model, @SessionAttribute("id") int userId){
		
//		userId = 3;
		/* 현재의 보유 자산 */
		long assetPresent = trendService.getAssetPresent(userId);
		/* 전날까지 또는 당일 오후 5시 이후의 자산 기록이 포함된 리스트 */
		List<RecordAsset> trendList = trendService.getRecordAssetList(userId);
		/* 자산 비율 리스트 */
		List<HaveView> distrList = distrService.getHaveStockList(userId);
		
	
		Map<String, Object> _json = new HashMap<>();
		_json.put("assetPresent", assetPresent);
		_json.put("trendList", trendList);
		_json.put("distrList", distrList);

		// Model 객체의 addAttribute에 키와 값을 담아서 View에게 전달한다.
		String json = new Gson().toJson(_json);
			
		return json;
		
	}
}
