package com.stockmarket.www.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockmarket.www.entity.InterestStocks;
import com.stockmarket.www.entity.KoreaStocks;
import com.stockmarket.www.service.CompanyService;

@Controller
@RequestMapping("/card/company/")
public class ListController extends HttpServlet {
	
	@Autowired
	private CompanyService companyService; //객체
	
	private List<KoreaStocks> searchCompanyList;
	private List<InterestStocks> interestStocksList;
	
	@GetMapping("list")
	public String list(Model model , HttpServletRequest request) {
		
		String companyName = "";
		String companyName_ = request.getParameter("companyName");
		
		if (companyName_ != null && !companyName_.equals("")) {
			companyName = companyName_;
		} else {
			return "card/company/list";
		
		}
		
		searchCompanyList = new ArrayList<KoreaStocks>();
		
		// 기현이 형의 검색 알고리즘을 통해 회사 이름을 List에 String 형식으로 담는다.
		List<String> list =	companyService.searchCompanyNames(companyName);
		
		// 위에 담긴 list에 있는 회사 종목을 하나씩 꺼내어 검색한 뒤에 회사정보를 koreaStocks 엔티티로  리턴한다.
		// koreaStocks 엔티티를 searchCompanyList에 담는다.
		for (int i = 0; i < list.size(); i++) {
			searchCompanyList.add(companyService.searchCompany(list.get(i))); 
		}
		
		// 로그인 유무 확인
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		int id = -1;

		if (tempId != null)
			id = (Integer) tempId;
		
		interestStocksList = new ArrayList<InterestStocks>();
		
		
		// 로그인이 되었으면 id를 파라미터 getInterestStocks 값을 가져온다.
		if (id != -1) {
			interestStocksList.addAll(companyService.getInterestStocks(id));
			System.out.println(companyService.getInterestStocks(id));
		}
		
		// getInterestStocks이 null이 아니면 request로 interestStocks을 보낸다
		if (interestStocksList != null) {
			model.addAttribute("interestStocks", interestStocksList);
			System.out.println(interestStocksList);
		}
		
		model.addAttribute("search", searchCompanyList);
		model.addAttribute("logIn", id);
		
		return "card/company/list";
		
	}
	
	@PostMapping("list-json")
	public String listJson(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		
		
		// session에서 아이디를 가져온다
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		int id = -1;
		
		if(tempId != null)
			id = (Integer)tempId;
		
		// attention과 status를 가져온다.
		String attention = request.getParameter("attention");
		String status = request.getParameter("status");
		
		
		//status가  insert일 경우에는 companyService.insertInterest에 아이디와 attention을 insert 한다.
		if (status.equals("insert")) {
			int result  = 0;
			result =companyService.insertInterest(id, attention);
			
		//status가  delete일 경우에는 companyService.deleteInterest에 아이디와 attention을 delete 한다.
		} else if (status.equals("delete")) {
			int result =0;
			result = companyService.deleteInterest(id, attention);
			
		};
		
		
		return "/card/company/list-json";
	}
	

}
