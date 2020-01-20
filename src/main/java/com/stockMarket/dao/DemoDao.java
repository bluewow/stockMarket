package com.stockMarket.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.stockMarket.entity.Demo;

@Mapper
public interface DemoDao {
	
//	@Select("select * from Test")
	Demo get();
}
