package com.stockmarket.www.service.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.entity.HaveView;
import com.stockmarket.www.entity.InterestStockView;
import com.stockmarket.www.entity.InterestView;
import com.stockmarket.www.service.HaveStockService;

@Service
public class BasicHaveStockService implements HaveStockService {

	@Autowired
	HaveStockDao haveStockDao;

	@Override
	public List<HaveView> getHaveStockList(int memberId) {
		// 크롤링 데이터 map
		Map<String, CurStock> map = new HashMap<String, CurStock>();
		// view list
		List<HaveView> haveList = new ArrayList<>();
		// db 데이터 list
		List<HaveStockView> haveStockView = haveStockDao.getList(memberId);

		if (AppContext.getStockMarket() != null)
			map.putAll(AppContext.getStockMarket());

		map.put("005930", new CurStock("005930", "3,000", "상승", "3,000", "+", "2.5"));
		map.put("123456", new CurStock("123456", "5,000", "하강", "3,000", "-", "3.4"));

		for (HaveStockView list : haveStockView) {
			String stockCode = list.getStockCode();
			String stockName = list.getStockName();
			int quantity = list.getQuantity();
			int sum = list.getSum();

			for (Entry<String, CurStock> data : map.entrySet()) {

				if (stockCode.equals(data.getKey())) {
					String price = data.getValue().getPrice();
					String gain = data.getValue().getGain();
					String percent = data.getValue().getPercent();
					HaveView haveView = new HaveView(memberId, stockCode, quantity, sum, stockName, price, gain,
							percent);
					haveList.add(haveView);
					break;
				}
			}
		}
		return haveList;
	}

	@Override
	public HaveView getHaveStockView(int memberId, String stockCode) {
		Map<String, CurStock> map = new HashMap<String, CurStock>();
		// view
		HaveView haveView = null;
		// db 데이터 list
		HaveStockView haveStockView = haveStockDao.getView(memberId, stockCode);

		if (AppContext.getStockMarket() != null)
			map.putAll(AppContext.getStockMarket());

		map.put("005930", new CurStock("005930", "3,000", "상승", "3,000", "+", "2.5"));
		map.put("123456", new CurStock("123456", "5,000", "하강", "3,000", "-", "3.4"));

		String stockName = haveStockView.getStockName();
		int quantity = haveStockView.getQuantity();
		int sum = haveStockView.getSum();

		for (Entry<String, CurStock> data : map.entrySet()) {

			if (stockCode.equals(data.getKey())) {
				String price = data.getValue().getPrice();
				String gain = data.getValue().getGain();
				String percent = data.getValue().getPercent();
				haveView = new HaveView(memberId, stockCode, quantity, sum, stockName, price, gain, percent);
				break;
			}
		}
		return haveView;
	}

	@Override
	public int updateHaveStock(HaveStock haveStock) {
		
		return haveStockDao.update(haveStock);
	}

	@Override
	public int insertHaveStock(HaveStock haveStock) {
		
		return haveStockDao.insert(haveStock);
	}

	@Override
	public int deleteHaveStock(int memberId, String stockCode) {
		
		return haveStockDao.delete(memberId, stockCode);
	}
}
