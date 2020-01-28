package com.stockmarket.www.entity;

import java.util.Date;

public class Member {
	private int id;
	private String email;
	private String password;
	private String nickName;
	private long vMoney;
	private int profileImg;
	private String cardPos;
	private long totalAsset;
	private Date regdate;
	
	public Member() {
		this(0, "", "", "", 0, 0, "", 0, null);		
	}
	
	// insert, update를 위한 생성자
	public Member(String email, String nickName, String password, long vMoney) {
		this(0, email, password, nickName, vMoney, 0, "", 0, null);
	}
	
	// select를 위한 생성자
	public Member(int id, String email, String nickName, String password, long vMoney, String cardPos, int profileImg) {
		this(id, email, password, nickName, vMoney, profileImg, cardPos, 0, null);
	}
	
	public Member(int id, String email, String password, String nickName, long vMoney, int profileImg, String cardPos, 
			long totalAsset, Date regdate) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.vMoney = vMoney;
		this.profileImg = profileImg;
		this.cardPos = cardPos;
		this.totalAsset = totalAsset;
		this.regdate = regdate;
	}

	// getter and setter
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public long getvMoney() {
		return vMoney;
	}
	
	public void setvMoney(long vMoney) {
		this.vMoney = vMoney;
	}

	public String getCardPos() {
		return cardPos;
	}

	public void setCardPos(String cardPos) {
		this.cardPos = cardPos;
	}

	public int getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(int profileImg) {
		this.profileImg = profileImg;
	}
	
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public long getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(long totalAsset) {
		this.totalAsset = totalAsset;
	}

	@Override
	public String toString() {
		return "id:" + id + ", " + 
				"email:" + email + ", " + 
				"nickName:" + nickName + ", " +
				"password:" + password + ", " +
				"vMoney:" + vMoney + ", " +
				"cardPos:" + cardPos + ", " +
				"profileImg:" + profileImg + ", " +
				"totalAsset:" + totalAsset + ", " +
				"regdate:" + regdate;
	}
}
