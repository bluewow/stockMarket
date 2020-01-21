package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.InterestView;

public interface InterestViewDao {

	List<InterestView> getInterestStockList(int id);

}
