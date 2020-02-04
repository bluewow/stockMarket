package com.stockmarket.www.service.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.entity.HaveView;
import com.stockmarket.www.service.AssetDistrService;
import com.stockmarket.www.service.AssetTrendService;
import com.stockmarket.www.service.HaveStockService;
import com.stockmarket.www.service.MemberService;

@Service
public class BasicAssetDistrService implements AssetDistrService{

	// Autowired를 통해 demoDao에 맞는 dao를 주입한다.
	@Autowired
	private HaveStockService haveStockService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AssetTrendService assetTrendService;

	/*
	 * 자산 분포도 ? 한 회원이 가지고 있는 모든 자산에서 회원이 보유하고 있는 주식 종목들의 자산 비율을 나타낸 것 한 종목의 자산 / 모든
	 * 종목의 자산을 더한 값 내림차순으로 정렬
	 */

	/* 멤버의 자산을 정렬 후 맵 리스트로 반환 */
	@Override
	public List<HaveView> getHaveStockList(int memberId) {
		
		List<HaveView> arrayList = new ArrayList<>();
		float profits = getSumAll(memberId);
		// System.out.println("profits: "+profits);
		
		List<HaveView> list = haveStockService.getHaveStockList(memberId);
		for (HaveView data : list) {
			HaveView haveView = new HaveView();
			float profit = getSumByStockId(data.getStockCode(), memberId);
			// System.out.println(data.getStockName()+"profit: "+profit);
			float ratio = (profit / profits) * 100;
			// System.out.println(data.getStockName()+"ratio: "+ratio);
			
			haveView.setStockCode(data.getStockCode());
			haveView.setStockName(data.getStockName());
			haveView.setRatio(ratio);
			haveView.setAssetValue(((long)profit));
		
			arrayList.add(haveView);
		}
		Collections.sort(arrayList, new Comparator<HaveView>() {
			@Override
			public int compare(HaveView v1, HaveView v2) {
				return Float.valueOf(v2.getRatio()).compareTo(Float.valueOf(v1.getRatio()));
			}
		});
		cutListForFour(arrayList);
		
		return arrayList;
	}
	
	/* 멤버의 하나의 보유 종목의 자산 */
	private float getSumByStockId(String stockCode, int memberId) {

		float presentPrice = Float.parseFloat(haveStockService.getHaveStockView(memberId, stockCode).getPrice().replaceAll(",", ""));
		int quantuty = haveStockService.getHaveStockView(memberId, stockCode).getQuantity();

		return presentPrice * quantuty;
	}

	/* 멤버의 모든 종목의 자산을 더한 값 = 현 보유 자산 - 주식 가상 머니 */
	private float getSumAll(int memberId) {

		long assetPresnt = assetTrendService.getAssetPresent(memberId);
		// System.out.println("assetPrsent: "+assetPresnt);
		long vMoney = memberService.getMember(memberId).getvMoney();

		return assetPresnt - vMoney;
	}
	
	/* 보유 주식이 5개 이상인 경우 4개 이후부터 기타로 묶고 4개 이후 리스트 삭제 */
	private void cutListForFour(List<HaveView> arrayList) {
		
		if(arrayList.size()>=5) {
			float ratio = 0;
			long assetValue = 0;
			for (int i = 0; i < arrayList.size()-3; i++) {
				ratio += arrayList.get(3+i).getRatio();
				assetValue += arrayList.get(3+i).getAssetValue();
			}
			while (arrayList.size()>4) {
				int i = 0;
				arrayList.remove(4+i);
				i++;
			}
			arrayList.get(3).setStockName("그 외");
			arrayList.get(3).setRatio(ratio);
			arrayList.get(3).setAssetValue(assetValue);
		}
	}
}
