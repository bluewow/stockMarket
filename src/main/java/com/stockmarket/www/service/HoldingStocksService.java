package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStockView;

public interface HoldingStocksService {

	List<HaveStockView> getInterestHoldingList(int userId);

}
