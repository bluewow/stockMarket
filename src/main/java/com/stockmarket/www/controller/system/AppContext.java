package com.stockmarket.www.controller.system;

import java.util.HashMap;
import java.util.Map;

import com.stockmarket.www.entity.CurStock;

//TODO static singleTone 방식이 아닌 boot 기능을 이용하자
public class AppContext {
	//Map<코드넘버, CurStock 객체> 
	static Map<String, CurStock> stockMarket = new HashMap<>(); 
	
    private AppContext() { }
    private static class SingleTonHolder {
        private static final AppContext instance = new AppContext();
    }
     
    public static AppContext getInstance() {
        return SingleTonHolder.instance;
    }
   
	public static Map<String, CurStock> getStockMarket() {
		return getInstance().stockMarket;
	}
}
