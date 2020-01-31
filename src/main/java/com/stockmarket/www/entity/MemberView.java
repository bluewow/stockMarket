package com.stockmarket.www.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MemberView extends Member {
	private int rank;
	private BigDecimal assetsGrowth;
	
	public MemberView() {
		
	}

	public MemberView(int id, String email, String password, String nickName, long vMoney, int profileImg,
			String cardPos, long totalAsset, Date regdate, int rank, BigDecimal assetsGrowth) {
		super(id, email, password, nickName, vMoney, profileImg, cardPos, totalAsset, regdate);
		this.rank = rank;
		this.assetsGrowth = assetsGrowth;		
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public BigDecimal getAssetsGrowth() {
		return assetsGrowth;
	}

	public void setAssetsGrowth(BigDecimal assetsGrowth) {
		this.assetsGrowth = assetsGrowth;
	}
	
	@Override
	public String toString() {
		return super.toString() + 
				"rank:" + rank + ", " +
				"assetsGrowth:" + assetsGrowth;
	}
}
