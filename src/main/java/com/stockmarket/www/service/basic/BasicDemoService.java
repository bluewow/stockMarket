package com.stockmarket.www.service.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.dao.DemoDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.DemoService;

// annotation인 @Service를 붙여서 해당 클래스가 Service라는 것을 spring에게 알려준다.
@Service
public class BasicDemoService implements DemoService{

	// Autowired를 통해 demoDao에 맞는 dao를 주입한다.
	@Autowired
	DemoDao demoDao;
	
	@Override
	public List<Member> getMemberList() {
		return demoDao.getMemberList();	// demoDao에 getList 호출
	}
	
	public static void main(String[] args) {
		BasicDemoService demo = new BasicDemoService();
		demo.getMemberList();
	}
}
