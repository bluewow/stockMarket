package com.stockmarket.www.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.stockmarket.www.entity.Demo;

@Mapper
public interface DemoDao {
	
//	@Select("select * from Test")
	Demo get();
}
