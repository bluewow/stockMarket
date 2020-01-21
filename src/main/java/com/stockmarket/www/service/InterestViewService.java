package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.InterestStocks;
import com.stockmarket.www.entity.InterestView;

public interface InterestViewService {

	List<InterestView> getInterestViewList(int id); 
	
}
