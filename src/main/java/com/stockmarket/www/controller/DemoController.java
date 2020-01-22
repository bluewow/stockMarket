package com.stockmarket.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.DemoService;

// annotation인 @Controller를 붙여서 해당 클래스가 Controller라는 것을 spring에게 알려준다.
@Controller
public class DemoController {

	// Autowired를 통해 Ioc 컨테이너 안에 존재하는 Bean을 자동으로 주입해준다.
	// 예제에서는 service 안에 BasicDemoService가 담기게 된다.
	@Autowired
	DemoService service;

	@GetMapping("/demo") // View의 요청 경로를 GetMapping을 통해 /demo로 설정한다.
	public String demo(Model model) {
		// service의 멤버 함수 getMemberList를 호출한다.
		// getMemberList는 예제를 위해 단순히 맴버들을 가져오는 함수이다.
		List<Member> members = service.getMemberList();

		// Model 객체의 addAttribute에 키와 값을 담아서 View에게 전달한다.
		model.addAttribute("members", members);

		// 뷰페이지가 있는 경로를 리턴하여 dispatcherServlet에게 전달한다.
		// 설정에서 prefix와 suffix를 설정하였기 때문에 아래의 리턴값은 "/WEB-INF/view/demo.jsp"가 된다.
		return "demo";
	}
}
