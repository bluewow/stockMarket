package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.HaveStockView;

public interface HaveStockDao {
	
	List<HaveStockView> getList(int memberId);
	
	HaveStock get(int memberId, String stockId);
	HaveStockView getView(int memberId, String stockId);

	int update(HaveStock haveStock);
	int insert(HaveStock haveStock);
	int delete(int memberId, String stockId);

}
