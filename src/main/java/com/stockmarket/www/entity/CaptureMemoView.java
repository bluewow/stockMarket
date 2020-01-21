package com.stockmarket.www.entity;

import java.util.Date;

public class CaptureMemoView extends CaptureMemo {
	private String codeNumName;

	public CaptureMemoView(String name, String title, Date regdate) {
		super(title, regdate);
		this.codeNumName = name;
	}
	
	public CaptureMemoView(int id, String name, String title, Date regdate) {
		super(id, title, regdate);
		this.codeNumName = name;
	}
	
	public String getCodeNumName() {
		return codeNumName;
	}

	public void setCodeNumName(String codeNumName) {
		this.codeNumName = codeNumName;
	}

	@Override
	public String toString() {
		return super.toString() + "["+ codeNumName + "]";
	}
}
