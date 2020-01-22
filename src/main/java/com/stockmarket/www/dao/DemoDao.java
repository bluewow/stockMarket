package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.stockmarket.www.entity.Member;

// Mapper annotation을 이용하여 Mapper Interface를
// Service layer에서 Bean으로 주입받아 사용 할 수 있다.
// Mapper에 관한 자세한 설명 : http://wiki.sys4u.co.kr/pages/viewpage.action?pageId=7767258
@Mapper
public interface DemoDao {
	List<Member> getMemberList();
}
