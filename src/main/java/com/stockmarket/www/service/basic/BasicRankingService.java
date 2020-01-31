package com.stockmarket.www.service.basic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.MemberView;
import com.stockmarket.www.service.RankingService;

@Service
public class BasicRankingService implements RankingService {
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public List<MemberView> getRankingList() {
		List<MemberView> members = memberDao.getRankerList();
		
		for (MemberView m : members) {
			BigDecimal assetsGrowth = BigDecimal.valueOf(m.getTotalAsset())
				.subtract(BigDecimal.valueOf(1000000))
				.divide(BigDecimal.valueOf(1000000))
				.setScale(2, RoundingMode.HALF_EVEN);
			
			m.setAssetsGrowth(assetsGrowth);
		
		}
		return members;
	}

	@Override
	public MemberView getMemberRank(int id) {
		MemberView member = memberDao.getMemberRank(id);
		BigDecimal assetsGrowth = BigDecimal.valueOf(member.getTotalAsset())
			.subtract(BigDecimal.valueOf(1000000))
			.divide(BigDecimal.valueOf(1000000))
			.setScale(2, RoundingMode.HALF_EVEN);
		
		member.setAssetsGrowth(assetsGrowth);
		
		return member;
	}
}
