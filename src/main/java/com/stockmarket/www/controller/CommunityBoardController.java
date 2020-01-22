package com.stockmarket.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;

@Controller
public class CommunityBoardController {
	
	@Autowired
	private CommunityBoardService service;
	
	@Autowired
	private MemberDao memberDao;
	
	//커뮤니티보드 기본 요청 리스트
	@GetMapping("/card/board/community_board")
	public String communityBoard(Model model, HttpServletRequest request) {
		// 세션을 이용하여 현재 사용자의 아이디를 가져온다.
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		int id = -1;

		if (tempId != null)
			id = (Integer) tempId;

		int page = 1;
		String field = "title";
		String query = "";
		String stockCode = "";

		String page_ = request.getParameter("p");
		if (page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);

		String field_ = request.getParameter("f");
		if (field_ != null && !field_.equals(""))
			field = field_;

		String query_ = request.getParameter("q");
		if (query_ != null && !query_.equals(""))
			query = query_;

		String stockCode_ = request.getParameter("s");
		if (stockCode_ != null && !stockCode_.equals(""))
			stockCode = stockCode_;

		model.addAttribute("CommunityBoard",
				service.getCommunityBoardList(page, field, query, stockCode, id)); // 컨트롤러가 할 일은 데이터를 준비하는
		model.addAttribute("loginId", id);

		return "communityBoard";
	}
	
	//커뮤니티보드 (페이지,필드,My) 요청 리스트
	@GetMapping("/card/board/community_board_list")
	public String communityBoardList(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		//로그인 세션을 불러온다.
		Object tempId = session.getAttribute("id");
		int loginId = -1;

		if (tempId != null)
			loginId = (Integer) tempId;
		

		String loginUser = memberDao.getMember(loginId).getNickName();

		//게시글목록을 불러온다.
		int page = 1;
		String field = "TITLE";
		String query = "";
		String stockName = "";

		String page_ = request.getParameter("p");
		if (page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);

		String field_ = request.getParameter("f");
		if (field_ != null && !field_.equals(""))
			field = field_;

		String query_ = request.getParameter("q");
		if (query_ != null && !query_.equals(""))
			query = query_;

		if(query.equals("my"))
			query = loginUser;

		String stockName_ = request.getParameter("s");
		if (stockName_ != null && !stockName_.equals(""))
			stockName = stockName_;
		List<CommunityBoard> list = service.getCommunityBoardList(page, field, query, stockName,loginId);

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("loginUser", loginUser);
		hm.put("list", list);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(hm);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json);
		return json;
	}
}
