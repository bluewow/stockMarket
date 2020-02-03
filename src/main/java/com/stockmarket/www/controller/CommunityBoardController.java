package com.stockmarket.www.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;

@Controller
@RequestMapping("/card/board/")
public class CommunityBoardController {
	
	@Autowired
	private CommunityBoardService service;
	
	@Autowired
	private MemberDao memberDao;
	
	//커뮤니티보드 기본 요청 리스트
	@GetMapping("community_board")
	public String communityBoard(@SessionAttribute("id") int id, @RequestParam(value="p", defaultValue = "1") int page, 
			@RequestParam(value="f", defaultValue = "title") String field, @RequestParam(value="q", defaultValue = "") String query, 
			@RequestParam(value="s", defaultValue = "") String stockCode, Model model) {

		model.addAttribute("CommunityBoard",
				service.getCommunityBoardList(page, field, query, stockCode, id)); // 컨트롤러가 할 일은 데이터를 준비하는
		model.addAttribute("loginId", id);

		return "card/board/community_board";
	}
	
	//커뮤니티보드 (페이지,필드,My) 요청 리스트
	@ResponseBody
	@GetMapping("community_board_list")
	public String communityBoardList(@SessionAttribute("id") int id, @RequestParam(value="p", defaultValue = "1") int page, 
			@RequestParam(value="f", defaultValue = "title") String field, @RequestParam(value="q", defaultValue = "") String query, 
			@RequestParam(value="s", defaultValue = "") String stockCode, Model model) {
		String loginUser = memberDao.getMember(id).getNickName(); //로그인아이디를 이용해서 닉네임을 가져온다.
		if(query.equals("my")) //쿼리가 my면 닉네임으로 정렬
			query = loginUser;
		List<CommunityBoard> list = service.getCommunityBoardList(page, field, query, stockCode, id);

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("loginUser", loginUser);
		hm.put("list", list);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(hm);
		return json;
	}
}
