package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.HaveStockView;

@Mapper
public interface HaveStockDao {
	
	List<HaveStockView> getList(int memberId);
	
	HaveStock get(int memberId, String stockCode);
	HaveStockView getView(int memberId, String stockCode);

	int update(HaveStock haveStock);
	int insert(HaveStock haveStock);
	int delete(int memberId, String stockCode);
}
