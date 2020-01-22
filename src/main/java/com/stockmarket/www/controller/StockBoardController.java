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
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.StockDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;

@Controller
public class StockBoardController {
	
	@Autowired
	private CommunityBoardService service;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private StockDao stockDao;
	
	//스톡보드 기본 요청 리스트
	@GetMapping("/card/board/stock_board")
	public String stockBoard(Model model, HttpServletRequest request) {

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

		model.addAttribute("CommunityBoard", service.getCommunityBoardList(page, field, query, stockCode, id)); // 컨트롤러가 할 일은 데이터를 준비하는 일
		model.addAttribute("loginId", id);

		
		return "stockBoard";
	}
	
	//종목 게시글(페이지,필드,My) 요청 리스트
	@GetMapping("/card/board/stock_board_list")
	public String stockBoardList(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		// 로그인 세션을 불러온다.
		Object tempId = session.getAttribute("id");
		int loginId = -1;

		if (tempId != null)
			loginId = (Integer) tempId;
		
		String loginUser = memberDao.getMember(loginId).getNickName();
		// 게시글목록을 불러온다.
		int page = 1;
		String field = "TITLE";
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
		
		if(query.equals("my"))
			query = loginUser;

		String stockCode_ = request.getParameter("s");
		if (stockCode_ != null && !stockCode_.equals(""))
			stockCode = stockCode_;
		

		
		String stockName = stockDao.getStockName(stockCode);

		List<CommunityBoard> list = service.getCommunityBoardList(page, field, query, stockCode, loginId);

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("loginUser", loginUser);
		hm.put("list", list);
		hm.put("stockName", stockName);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(hm);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json);
		return "stockBoardList";
	}
	
	//게시글 내용과 댓글 확인
	@GetMapping("/card/board/detail")
	public String detail(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		int loginId = -1;

		if (tempId != null)
			loginId = (Integer) tempId;
		String loginUser = memberDao.getMember(loginId).getNickName();
		int boardId = Integer.parseInt(request.getParameter("id"));

		//CommunityBoard list = communityBoardService.getBoard(boardId);
		model.addAttribute("detail", service.getBoard(boardId));
		model.addAttribute("loginUser", loginUser);
				
		return "detail";
		
	}
	
	//게시글 등록
	@PostMapping("/card/board/stock_reg_board")
	public String RegBoard(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		String boardIds = request.getParameter("boardId");
		String stockCode = request.getParameter("stockCode");

		// 상태값이 reg면 등록
		if (status.equals("reg")) {
			Object tempId = session.getAttribute("id");
			int writerId = -1;

			if (tempId != null)
				writerId = (Integer) tempId;
			String writerNickname = memberDao.getMember(writerId).getNickName();

			CommunityBoard insertBoard = new CommunityBoard(title, content, writerNickname, stockCode);

			int result = service.insertCommunityBoard(insertBoard);

			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
			PrintWriter out = response.getWriter();
			out.print(result);

			// 상태값이del이면 삭제
		} else if (status.equals("del")) {
			int boardId = -1;
			boardId = Integer.parseInt(boardIds);
			int resultInterest = service.deleteinterestBoards(boardId);
			int result = service.deleteCommunityBoard(boardId);
			int resultReply = service.deleteReplys(boardId);

			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
			PrintWriter out = response.getWriter();

			out.print(result);

			// 상태값이 modi면 수정
		} else if (status.equals("modi")) {
			int boardId = -1;
			boardId = Integer.parseInt(boardIds);

			CommunityBoard updateCommunityBoard = new CommunityBoard(boardId, title, content, "modi");
			int result = service.updateCommunityBoard(updateCommunityBoard);

			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
			PrintWriter out = response.getWriter();
			out.print(result);
		}
		return "regBoard";
	}
	
	//댓글 삽입, 수정, 삭제
	@PostMapping("/card/board/Reply")
	public String Reply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 보내준 값으로 삽입, 수정,삭제여부 확인
		HttpSession session = request.getSession();
		String reContent = request.getParameter("reContent");
		String boardId_ = request.getParameter("boardId");
		String replyIds = request.getParameter("replyId");
		String status = request.getParameter("status");

		// 상태값이 없으면 삽입
		if (status == null) {
			Object tempId = session.getAttribute("id");
			int writerId = -1;

			if (tempId != null)
				writerId = (Integer) tempId;
			String writerNickname = memberDao.getMember(writerId).getNickName();

			int boardId = Integer.parseInt(boardId_);
			CommunityBoard insertReply = new CommunityBoard(reContent, writerNickname, boardId);

			int result = service.insertReply(insertReply);
			int lastReplyNum = service.lastReplyNum(boardId);

			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
			PrintWriter out = response.getWriter();
			out.print(lastReplyNum);

			// 상태값에 del이면 삭제
		} else if (status.equals("del")) {
			int replyId = -1;
			replyId = Integer.parseInt(replyIds);

			// CommunityBoard deleteReply = new CommunityBoard(replyId, "del");
			int result = service.deleteReply(replyId);

			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
			PrintWriter out = response.getWriter();

			out.print(result);

			// 상태값에 modi면 수정
		} else if (status.equals("modi")) {
			int replyId = -1;
			replyId = Integer.parseInt(replyIds);

			CommunityBoard updateReply = new CommunityBoard(replyId, reContent, "del");
			int result = service.updateReply(updateReply);

			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
			PrintWriter out = response.getWriter();

			out.print(result);

		}
		return "reply";
	}
	
	//즐겨찾기 추가,삭제
	@PostMapping("/card/board/interest")
	public String interestBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String status = request.getParameter("status");
		Object tempId = session.getAttribute("id");
		int loginId = -1;
		if (tempId != null)
			loginId = (Integer) tempId;
		
		if (status.equals("check")) {
			CommunityBoard selectInterestBoard = new CommunityBoard(boardId, loginId, status);
			int result = service.selectInterestBoard(selectInterestBoard);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(result);
		} else if (status.equals("insert")) {
			CommunityBoard insertInterestBoard = new CommunityBoard(boardId, loginId, status);
			int result = service.insertInterestBoard(insertInterestBoard);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(result);

		} else if (status.equals("delete")) {
			CommunityBoard deleteInterestBoard = new CommunityBoard(boardId, loginId, status);
			int result = service.deleteInterestBoard(deleteInterestBoard);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(result);

		}
		return "interestBoard";
	
	}
}
