package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.InterestView;


@Mapper
public interface InterestViewDao {
	
	List<InterestView> getInterestStockList(int id);

}
