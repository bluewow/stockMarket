package com.stockmarket.www.entity;

import java.util.Date;

public class CommunityBoard {
	private int id;
	private int loginId;
	private String title;
	private String writerId;
	private Date regdate;
	private int hit;
	private String content;
	private String companyName;
	private String stockCode;
	private int replyCnt;
	private int replyId;
	private String reContent;
	private int interest;

// select

	public CommunityBoard() {
	}

	public CommunityBoard(int id, String title, String writerId, Date regdate, int hit, String companyName,
			int replyCnt, int interest) {
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.regdate = regdate;
		this.hit = hit;
		this.companyName = companyName;
		this.replyCnt = replyCnt;
		this.interest = interest;
	}

// insert, update

	public CommunityBoard(int id, String title, String writerId, Date regdate, int hit, String content,
			String companyName) {
		super();
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.regdate = regdate;
		this.hit = hit;
		this.content = content;
		this.companyName = companyName;
	}

	public CommunityBoard(int replyId, String reContent, String writerId, Date regdate, int boardId) {
		super();
		this.replyId = replyId;
		this.reContent = reContent;
		this.writerId = writerId;
		this.regdate = regdate;
		this.id = boardId;
	}

	public CommunityBoard(String title, String content, String writerId, String stockCode) {
		super();
		this.title = title;
		this.content = content;
		this.writerId = writerId;
		this.stockCode = stockCode;
	}

	public CommunityBoard(int boardId) {
		this.id = boardId;
	}

// reply insert
	public CommunityBoard(String reContent, String writerId, int boardId) {
		super();
		this.reContent = reContent;
		this.writerId = writerId;
		this.id = boardId;
	}

//reply delete
	public CommunityBoard(int replyId, String string) {
		this.replyId = replyId;
	}

//reply update
	public CommunityBoard(int replyId, String reContent, String string) {
		this.replyId = replyId;
		this.reContent = reContent;
	}
	
//update CommunityBoard
	public CommunityBoard(int id, String title, String content, String modi) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public CommunityBoard(int boardId, int loginId) {
		this.id = boardId;
		this.setLoginId(loginId);
	}

	public CommunityBoard(int id, int boardId, int loginId) {
		this.id = boardId;
		this.setLoginId(loginId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	@Override
	public String toString() {
		return "CommunityBoard [id=" + id + ", loginId=" + loginId + ", title=" + title + ", writerId=" + writerId
				+ ", regdate=" + regdate + ", hit=" + hit + ", content=" + content + ", companyName=" + companyName
				+ ", stockCode=" + stockCode + ", replyCnt=" + replyCnt + ", replyId=" + replyId + ", reContent="
				+ reContent + ", interest=" + interest + "]";
	}

}
