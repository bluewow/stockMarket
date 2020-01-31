package com.stockmarket.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.stockmarket.www.entity.MemberView;
import com.stockmarket.www.service.RankingService;

@Controller
@RequestMapping("/card/rank/")
public class RankingController {
	
	@Autowired
	private RankingService service;
	
	@GetMapping("ranking")
	public String rank(Model model, @SessionAttribute("id") int id) {
		// 보유 자산 상위 50위까지 가져온다.
		List<MemberView> rankers = service.getRankingList();
		
		// Model 객체를 이용하여 보유 자산 상위 50명의 정보를 View에 전달
		model.addAttribute("rankList", rankers);
		
		// 본인의 랭킹을 가져온다.
		MemberView myRank = service.getMemberRank(id);
		
		// Model 객체를 이용하여 본인의 랭킹정보를 View에 전달
		model.addAttribute("myRank", myRank);
		
		return "card/rank/rank";
	}
}
