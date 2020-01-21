package com.stockmarket.www.entity;

public class Member {
	private int id;
	private String email;
	private String nickName;
	private String password;
	private long vMoney;
	private String cardPos;
	private int profileImg;
	
	public Member() {
	
	}
	
	// insert, update를 위한 생성자
	public Member(String email, String nickName, String password, long vMoney) {
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.vMoney = vMoney;
	}
	
	// select를 위한 생성자
	public Member(int id, String email, String nickName, String password, long vMoney, String cardPos, int profileImg) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.vMoney = vMoney;
		this.cardPos = cardPos;
		this.profileImg = profileImg;
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
	
	@Override
	public String toString() {
		return id + "," + 
		  email + "," + 
		  nickName + "," +
		  password + "," +
		  vMoney + "," +
		 cardPos + "," +
		 profileImg;
	}
}
