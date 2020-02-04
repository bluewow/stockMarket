package com.stockmarket.www.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stockmarket.www.dao.KoreaStocksDao;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;

@Controller
@RequestMapping("/card/board/")
public class StockBoardController {
	
	@Autowired
	private CommunityBoardService service;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private KoreaStocksDao stockDao;
	
	//스톡보드 기본 요청 리스트
	@GetMapping("stock_board")
	public String StockBoard(@SessionAttribute("id") int id, @RequestParam(value="p", defaultValue = "1") int page, 
			@RequestParam(value="f", defaultValue = "title") String field, @RequestParam(value="q", defaultValue = "") String query, 
			@RequestParam(value="s", defaultValue = "") String stockCode, Model model) {
		model.addAttribute("CommunityBoard", service.getCommunityBoardList(page, field, query, stockCode, id)); // 컨트롤러가 할 일은 데이터를 준비하는 일
		model.addAttribute("loginId", id);

		return "card/board/stock_board";
	}
	
	//종목 게시글(페이지,필드,My) 요청 리스트
	@ResponseBody
	@GetMapping("stock_board_list")
	public String StockBoardList(@SessionAttribute("id") int id, @RequestParam(value="p", defaultValue = "1") int page, 
			@RequestParam(value="f", defaultValue = "title") String field, @RequestParam(value="q", defaultValue = "") String query, 
			@RequestParam(value="s", defaultValue = "") String stockCode, Model model) {
		String loginUser = memberDao.getMember(id).getNickName(); //로그인아이디를 이용해서 닉네임을 가져온다.
		String stockName = stockDao.get(stockCode).getCompanyName();
		if(query.equals("my")) //쿼리가 my면 닉네임으로 정렬
			query = loginUser;
		List<CommunityBoard> list = service.getCommunityBoardList(page, field, query, stockCode, id);
		System.out.println(list);
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("loginUser", loginUser);
		hm.put("list", list);
		hm.put("stockName", stockName);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(hm);
		return json;
	}
	
	//게시글 내용과 댓글 확인
	@ResponseBody
	@GetMapping("detail")
	public String Detail(@SessionAttribute("id") int loginId, 
			@RequestParam(value="id") int boardId, Model model) {
		String loginUser = memberDao.getMember(loginId).getNickName();
		CommunityBoard communityBoard = service.getBoard(boardId);
		List<CommunityBoard> replyList = service.getCommunityBoardReplyList(boardId);
		communityBoard.setHit(communityBoard.getHit()+1);
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("loginUser", loginUser);
		hm.put("board", communityBoard);
		hm.put("replys", replyList);
		service.updateCommunityBoard(communityBoard);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String Json = gson.toJson(hm);
		
		return Json;
	}
	
	//게시글 등록
	@ResponseBody
	@PostMapping("stock_reg_board")
	public int RegBoard(@SessionAttribute("id") int id, @RequestParam String title, 
			@RequestParam String content, @RequestParam String stockCode, Model model) {
			String writerNickname = memberDao.getMember(id).getNickName();
			CommunityBoard insertBoard = new CommunityBoard(title, content, writerNickname, stockCode);
			int result = service.insertCommunityBoard(insertBoard);
			return result;
	}
	
	//게시글 수정
	@ResponseBody
	@PostMapping("stock_update_board")
	public int UpdateBoard(@SessionAttribute("id") int id, @RequestParam String title, 
			@RequestParam String content, @RequestParam int boardId, 
			@RequestParam String stockCode, Model model) {
		
		CommunityBoard updateCommunityBoard = new CommunityBoard(boardId, title, content, "modi");
		int result = service.updateCommunityBoard(updateCommunityBoard);
			return result;
	}
	
	//게시글 삭제
	@ResponseBody
	@PostMapping("stock_delete_board")
	public int DeleteBoard(@RequestParam int boardId) {
		service.deleteReplys(boardId);
		service.deleteInterestBoards(boardId);
		int result = service.deleteCommunityBoard(boardId);
		
		return result;
	}
	
	//댓글 삽입
	@ResponseBody
	@PostMapping("reply_insert")
	public int ReplyInsert(@SessionAttribute("id") int id, @RequestParam String reContent, 
			@RequestParam int boardId) {
		String writerId = memberDao.getMember(id).getNickName();
		CommunityBoard insertReply = new CommunityBoard(reContent, writerId, boardId);
		service.insertReply(insertReply);
		int lastReplyNum = service.lastReplyNum();
		
		return lastReplyNum;
	}
	
	//댓글 수정
	@ResponseBody
	@PostMapping("reply_update")
	public int ReplyUpdate(@SessionAttribute("id") int id, @RequestParam(required = false) String reContent,
			@RequestParam(defaultValue = "0") int replyId) {
		CommunityBoard updateReply = new CommunityBoard(replyId, reContent, "reply");
		int result = service.updateReply(updateReply);

		return result;
	}
	
	//댓글 삭제
	@ResponseBody
	@PostMapping("reply_delete")
	public int ReplyDelete(@RequestParam int replyId) {
		System.out.println(replyId);
		int result = service.deleteReply(replyId);

		return result;
	}

	//즐겨찾기 insert
	@ResponseBody
	@PostMapping("interest_insert")
	public int InterestBoardInsert(@SessionAttribute("id") int id, @RequestParam int boardId) {
			CommunityBoard insertInterestBoard = new CommunityBoard(boardId, id);
			int result = service.insertInterestBoard(insertInterestBoard);
	
			return result;
	}
	
	//즐겨찾기 delete
	@ResponseBody
	@PostMapping("interest_delete")
	public int InterestBoardDelete(@SessionAttribute("id") int id, @RequestParam int boardId) {
			CommunityBoard deleteInterestBoard = new CommunityBoard(boardId, id);
			int result = service.deleteInterestBoard(deleteInterestBoard);
	
			return result;
	}
}
