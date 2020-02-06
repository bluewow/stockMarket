package com.stockmarket.www.service.basic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stockmarket.www.entity.HaveView;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.service.AssetTrendService;
import com.stockmarket.www.service.HaveStockService;
import com.stockmarket.www.service.MemberService;
import com.stockmarket.www.service.RecordAssetService;

@Service
public class BasicAssetTrendService implements AssetTrendService {

	@Autowired
	private RecordAssetService recordAssetService; 
	@Autowired
	private HaveStockService haveStockService;
	@Autowired
	private MemberService memberService;
	

	@Override
	public List<RecordAsset> getRecordAssetList(int memberId) {
		
		return recordAssetService.getRecordAssetList(memberId);
	}

	@Override
	public long getAssetPresent(int memberId) {
		// 현재 보유 자산
		long sum = 0;
		sum = memberService.getMember(memberId).getvMoney();
		
		List<HaveView> list = haveStockService.getHaveStockList(memberId);
		for (HaveView data : list) {
			// (보유종목당 현재가 및 보유수량 확인용)
			// System.out.println(data.getPrice()+","+data.getQuantity()); 
			long presentValue = Long.parseLong(data.getPrice().replaceAll(",",""));
			long quantity = data.getQuantity();
			// System.out.println(data.getStockName()+": "+presentValue*quantity);
			sum += presentValue*quantity;
		}
		// 가상머니+(현재가*보유수량)+(현재가*보유수량).....
		// List<HaveStock> quantHaveStocks = haveStockDao.getQuantity(memberId);
		return sum;
	}

}
