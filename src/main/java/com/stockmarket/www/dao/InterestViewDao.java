package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.InterestStockView;


@Mapper
public interface InterestViewDao {
	
	List<InterestStockView> getInterestStockList(int id);

}
