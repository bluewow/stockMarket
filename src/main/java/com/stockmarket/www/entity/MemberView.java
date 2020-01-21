package com.stockmarket.www.entity;

public class MemberView extends Member {
	private long totalAsset;
	
	public MemberView(int id, String email, String nickName, String password, long vMoney, String cardPos, int profileImg) {
		super(id, email, nickName, password, vMoney, cardPos, profileImg);
	}

	public long getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(long totalAsset) {
		this.totalAsset = totalAsset;
	}
}
