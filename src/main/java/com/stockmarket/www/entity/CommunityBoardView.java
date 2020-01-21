package com.stockmarket.www.entity;

import java.util.Date;

public class CommunityBoardView extends CommunityBoard {
	private int replyCnt;

	public CommunityBoardView() {

	}

	public CommunityBoardView(int id, String title, String writerId, Date regdate, int hit,
								String content, String stockName, int replyCnt) {
		super(id, title, writerId, regdate, hit, content, stockName);
		this.replyCnt = replyCnt;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

}
