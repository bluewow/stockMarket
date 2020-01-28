//package com.stockmarket.www.controller;
//
//import java.io.IOException;
//import java.util.Comparator;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.stockmarket.www.entity.Member;
//import com.stockmarket.www.entity.MemberView;
//import com.stockmarket.www.service.RankingService;
//import com.stockmarket.www.service.RankingService;
//import com.stockmarket.www.service.basic.BasicRankingService;
//
//@Controller
//@RequestMapping("/card/rank/")
//public class RankingController {
//	
//	@Autowired
//	private RankingService service;
//	
//	@GetMapping("rank")
//	public String rank(Model model, HttpServletRequest request) {
//		// 보유 자산 상위 50위까지 가져온다.
//		List<MemberView> rankers = service.getMemberList();
//		
//		rankers.sort(new Comparator<MemberView>() {
//			@Override
//			public int compare(MemberView o1, MemberView o2) {
//				long x = o1.getTotalAsset();
//				long y = o2.getTotalAsset();
//				
//				if(x>y) return -1;
//				else if(x<y) return 1;
//				else return 0;
//			}
//		});
//		
//		// Model 객체를 이용하여 보유 자산 상위 50명을 View에 전달
//		model.addAttribute("rankers", rankers);
//		
//		// 세션에 저장되어 있는 사용자의 아이디를 가져온다.
//		HttpSession session = request.getSession();
//		int id = 1;
////		Object tempId = session.getAttribute("id");
////				
////		if(tempId != null)
////			id = (Integer)tempId;
//		
//		// 본인의 랭킹을 가져온다.
//		MemberView myInfo = service.getMember(id);
//		
//		model.addAttribute("myRank", service.getMemberRank(id));
//		model.addAttribute("myInfo", myInfo);
//		
//		return "card/rank/rank";
//	}
//}
