package com.stockmarket.www.service.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		// 크롤링 데이터 map
//		Map<String, CurStock> map = new HashMap<String, CurStock>();
		// view list
		List<InterestView> interestlist = new ArrayList<>();
		// db 데이터 list
		List<InterestStockView> interestStockView = interestViewDao.getInterestStockList(id);

//		if (AppContext.getStockMarket() != null)
//			map.putAll(AppContext.getStockMarket());

		Map<String, CurStock> map = new HashMap<String, CurStock>();
		map.put("035420", new CurStock("035420", "3,000", "상승", "3,000", "+", "2.5"));
		map.put("000660", new CurStock("000660", "5,000", "하강", "3,000", "-", "3.4"));
		map.put("020560", new CurStock("020560", "6,000", "보합", "3,000", "0.0", "1.5"));
		map.put("005930", new CurStock("005930", "2,000", "상승", "3,000", "+", "1.6"));
		map.put("005380", new CurStock("005380", "1,000", "상승", "3,000", "+", "8.9"));
		map.put("095660", new CurStock("095660", "10,500", "상승", "3,000", "+", "10.2"));
		map.put("217500", new CurStock("217500", "3,500", "하강", "3,000", "-", "14.2"));
		map.put("215600", new CurStock("215600", "7,000", "하강", "3,000", "-", "10"));

		
		
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
