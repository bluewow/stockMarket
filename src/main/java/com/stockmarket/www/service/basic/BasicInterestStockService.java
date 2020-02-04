package com.stockmarket.www.service.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.dao.InterestViewDao;
import com.stockmarket.www.dao.KoreaStocksDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.InterestStockView;
import com.stockmarket.www.entity.InterestStocks;
import com.stockmarket.www.entity.InterestView;
import com.stockmarket.www.entity.KoreaStocks;
import com.stockmarket.www.service.InterestStockService;

@Service
public class BasicInterestStockService implements InterestStockService {

	@Autowired
	KoreaStocksDao koreaStocksDao;

	@Autowired
	InterestViewDao interestViewDao;

	@Autowired
	InterestStocksDao interestStockDao;

	@Override
	public List<InterestView> getInterestViewList(int id) {

		Map<String, CurStock> map = new HashMap<String, CurStock>();

		// 크롤링 데이터 map
//		Map<String, CurStock> map = new HashMap<String, CurStock>();
		// view list
		List<InterestView> interestlist = new ArrayList<>();
		// db 데이터 list
		List<InterestStockView> interestStockView = interestViewDao.getInterestStockList(id);

		if (AppContext.getStockMarket() != null)
			map.putAll(AppContext.getStockMarket());
		
		if (!interestStockView.isEmpty()) {
			for (InterestStockView rs : interestStockView) {
				String stockName = rs.getStockName();
				String stockId = rs.getStockCode();

				for (Entry<String, CurStock> data : map.entrySet()) {
					if (stockId.equals(data.getValue().getCodeNum())) {
						String price = data.getValue().getPrice();
						String gain = data.getValue().getGain();
						String percent = data.getValue().getPercent();
						InterestView interestview = new InterestView(stockName, price, percent, gain);
						interestlist.add(interestview);
						break;
					}
				}
			}
			return interestlist;
		} else {
			return interestlist;
		}
	}

	@Override
	public List<InterestStocks> getInterestStockList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteStock(int userid, String delStockName) {
		KoreaStocks koreaStocks = koreaStocksDao.searchCompany(delStockName);
		return interestStockDao.delete(userid, koreaStocks.getStockCode());
	}

}
