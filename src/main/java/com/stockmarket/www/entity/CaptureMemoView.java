package com.stockmarket.www.entity;

import java.util.Date;

public class CaptureMemoView extends CaptureMemo {
	private String companyName;

	public CaptureMemoView() {
		
	}
	
	public CaptureMemoView(String name, String title, Date regdate) {
		super(title, regdate);
		this.companyName = name;
	}
	
	public CaptureMemoView(int id, String name, String title, Date regdate) {
		super(id, title, regdate);
		this.companyName = name;
	}
	
	public String getCodeNumName() {
		return companyName;
	}

	public void setCodeNumName(String codeNumName) {
		this.companyName = codeNumName;
	}

	@Override
	public String toString() {
		return super.toString() + "["+ companyName + "]";
	}
}
