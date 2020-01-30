package com.stockmarket.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CaptureMemoView;
import com.stockmarket.www.service.CaptureMemoService;

@Controller
@RequestMapping("/card/capturememo/")
public class CaptureMemoController {
	@Autowired
	private CaptureMemoService service;

	@GetMapping("captureMemo")
	public String captureMemo() {
		return "card/capturememo/captureMemo";
	}

	@ResponseBody
	@GetMapping("captureMemo-json-list")
	public String captureMemoList(@SessionAttribute("id") int id) {
		List<CaptureMemoView> captureMemoViews = service.getList(id);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(captureMemoViews);
		return json;
	}

	@ResponseBody
	@GetMapping("captureMemo-json-detail")
	public String captureMemoDetail(@RequestParam int memoId) {
		CaptureMemo captureMemo = service.get(memoId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(captureMemo);
		return json;
	}

	@ResponseBody
	@PostMapping("captureMemo-json-insert")
	public int captureMemoInsert(@RequestBody String param) {
		System.out.println(param);
		// json 형태의 data 파싱
//		Gson gson = new Gson();
//		CaptureMemoView memoView = gson.fromJson(jb.toString(), CaptureMemoView.class);
//
//		int result = service.insert(memoView);

//		return result;
		return 1;
	}

	@ResponseBody
	@PostMapping("captureMemo-json-update")
	public int captureMemoUpdate(@RequestBody String param) {
		Gson gson = new Gson();
		CaptureMemo memo = gson.fromJson(param, CaptureMemo.class);
		
		int result = service.update(memo);
		return result;
	}
	
	@ResponseBody
	@GetMapping("captureMemo-json-delete")
	public int captureMemoDelete(@RequestParam int memoId) {
		int result = service.delete(memoId);
		return result;
	}

	@ResponseBody
	@GetMapping("captureMemo-json-dataCrawling")
	public String captureMemoDataCrawling(@RequestParam int id) {
		CaptureMemo captureMemo = service.get(id);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(service.captureDataCrawling(captureMemo.getCodeNum(), captureMemo.getMemberId()));
		return json;
	}

}