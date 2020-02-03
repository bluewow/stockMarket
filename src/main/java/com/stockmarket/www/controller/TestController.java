package com.stockmarket.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.Member;

@RestController
public class TestController {
	@Autowired
	private MemberDao dao;
	
//	@Autowired
//	private CaptureMemoDao dao;

	@ResponseBody
	@GetMapping("/test")
	public String test() {
		// ###Member mapper Test#############################################
		// getMemberList Test
		List<Member> list = dao.getMemberList();
		for(Member m : list)
			System.out.println(m.toString());
		
		// getRankerList Test
//		List<Member> list = dao.getRankerList();
//		for(Member m : list)
//			System.out.println(m.toString());
		
//		getMember Test
//		Member member = dao.getMember(1);
//		System.out.println(member.toString());
		
//		getMemberByEmail Test
//		Member member = dao.getMemberByEmail("sylvester127@naver.com");
//		System.out.println(member.toString());
		
//		updateMember Test
//		Member member = dao.getMember(1);
//		member.setvMoney(111113);
//		int result = dao.updateMember(member);
//		System.out.println(result);
		
		// isDuplicatedId Test
//		boolean result = dao.isDuplicatedId("kimnr");
//		System.out.println(result);
		
		// ###CaptureMemo mapper Test#############################################
		// get Test
//		CaptureMemo memo = dao.get(2);
//		System.out.println(memo.toString());
		
		// update Test
//		CaptureMemo memo = dao.get(2);
//		memo.setTitle("제목 테스트");
//		memo.setContent("내용 테스트");
//		int result = dao.update(memo);
//		System.out.println(result);
		
		// delete Test
//		int result = dao.delete(4);
//		System.out.println(result);
		
		return "";
	}
}
