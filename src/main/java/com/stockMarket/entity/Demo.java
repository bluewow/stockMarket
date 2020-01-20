package com.stockMarket.entity;

import java.util.Date;

public class Demo {
	int id;
	Date createTime;
	Date updateTime;
	String content;

	public Demo() {
		this(0, null, null, "");
	}
	
	public Demo(int id, Date createTime, Date updateTime, String content) {
		this.id = id;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return id + "," + createTime + "," + updateTime + "," + content;
	}
}
