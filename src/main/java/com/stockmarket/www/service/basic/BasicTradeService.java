package com.stockmarket.www.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.KoreaStocksDao;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.StockDetailDao;
import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.TradeService;

@Service
public class BasicTradeService implements TradeService{
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	HaveStockDao stockDao;
	
	@Autowired
	StockDetailDao stockDetailDao;
	
	@Autowired
	KoreaStocksDao koreaStockDao;
	
	@Override
	public long getAssets(int id) {
		Member member = memberDao.getMember(id);
		if(member == null)
			return 0;
		
		return member.getvMoney();
	}

	@Override
	public int getStockAssets(int id, String stockId) {
		HaveStock haveStock = stockDao.get(id, stockId);
		if(haveStock == null)
			return 0;
		
		return haveStock.getSum();
	}
	
	@Override
	public int getQty(int id, String stockId) {
		HaveStock haveStock = stockDao.get(id, stockId);
		if(haveStock == null)
			return 0;
		
		return haveStock.getQuantity();
	}
	
	@Override
	public void setQty(int id, String stockId, int qty, int curPrice) {
		
		HaveStock haveStock = stockDao.get(id, stockId);
			
		Member member = memberDao.getMember(id);
		member.setvMoney(member.getvMoney() + (-qty * curPrice));
		memberDao.updateMember(member);
		haveStock.setQuantity(haveStock.getQuantity() + qty);
		haveStock.setSum(haveStock.getSum() + qty * curPrice);
		stockDao.update(haveStock);
	}
	

	@Override
	public int checkVmoney(int id, int qty, int curStockPrice) {
		Member member = memberDao.getMember(id);
		int buyMoney = qty * curStockPrice;
		
		if(member.getvMoney() < buyMoney)
			return 1; //vmoney 부족
		
		return 0;
	}
	
	@Override
	public boolean checkHaveStock(int id, String codeNum) {
		HaveStock haveStock = stockDao.get(id, codeNum);
		if(haveStock == null)
			return false;
		
		return true;
	}
	
	@Override
	public boolean checkMinusHaveStock(int id, String codeNum, int qty) {
		HaveStock haveStock = stockDao.get(id, codeNum);
		//마이나스 수량 체크
		if(haveStock.getQuantity() - qty < 0) 
			return true;
		
		return false;
	}
	
	@Override
	public boolean checkZeroHaveStock(int id, String codeNum) {
		HaveStock haveStock = stockDao.get(id, codeNum);
		//Zero 수량 체크
		if(haveStock.getQuantity() == 0) 
			return true;
		
		return false;
	}
	
	
	@Override
	public void addHaveStock(int id, String codeNum) {
		HaveStock haveStock = new HaveStock(id, codeNum, 0, 0);
		stockDao.insert(haveStock);
	};
	
	@Override
	public void delHaveStock(int memberId, String codeNum) {
		stockDao.delete(memberId, codeNum);
	}
	
	@Override
	public void trade(int id, String codeNum, int qty, int curStockPrice) {
		Member member = memberDao.getMember(id);
		HaveStock haveStock = stockDao.get(id, codeNum);
		
		//회원의 가상머니 수정
		member.setvMoney(member.getvMoney() + (-qty * curStockPrice));
		memberDao.updateMember(member);

		//보유종목에 대한 수량과 sum 수정
		haveStock.setQuantity(haveStock.getQuantity() + qty);
		haveStock.setSum(haveStock.getSum() + qty * curStockPrice);
		stockDao.update(haveStock);
	}
	
	@Override
	public String getCompanyName(String codeNum) {
		return koreaStockDao.get(codeNum).getCompanyName();
	}
}	

