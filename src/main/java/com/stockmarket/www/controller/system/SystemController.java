package com.stockmarket.www.controller.system;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.www.service.SystemService;
import com.stockmarket.www.service.basic.BasicSystemAnalysis;

@RestController
public class SystemController {
	static boolean oneShotFlag;
	static String preHour; 
	
	@Autowired
	private SystemService service;
	
	@Autowired
	private BasicSystemAnalysis systemAnalysis;
	
	@Autowired 
	private HttpServletRequest req;
	
	public SystemController() {
		oneShotFlag = false;
	}

	@GetMapping("/system")
	public void systemInit() {
		if(oneShotFlag == true) {
			return;
		}
		oneShotFlag = true;
		
		Thread thread = new Thread(()->{
			try {
				while(!Thread.currentThread().isInterrupted())
					systemThread();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				System.out.println("Thread is dead");
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	private void systemThread() throws InterruptedException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("HH"); //HH : 0~23시  기타형식예 "yyyy-MM-dd HH:mm:ss"
		String curHour = date.format(System.currentTimeMillis());
		
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH : 0~23시  기타형식예 "yyyy-MM-dd HH:mm:ss"
		System.out.println(date1.format(System.currentTimeMillis()));
		//10분주기 - refreshStockPrice 함수실행시 약 7분소요로 10분주기로 변경
		
		//오전 5시 하루에 한번 KOSPI.csv KOSDAQ.csv 파일을 갱신한다.
		if(curHour.equals("5") && preHour.equals("4")) {
			service.updateMarket("KOSPI");
			service.updateMarket("KOSDAQ");
		}
			
		//주식가격 refresh by 크롤링 9시 ~ 20시까지 실행
		if(Integer.parseInt(curHour) >= 9 && Integer.parseInt(curHour) <= 19) {
			service.refreshStockPrice();
		}
		//18시 장종료후 19시 주식데이터 갱신 stockDetail 데이터
		if(curHour.equals("20") && preHour.equals("19")) {
//			TODO
		}
		
		if(curHour.equals("19") && preHour.equals("18")) {
			service.insertRecordAsset();
		}
		
		//매일 오전 8시 분석데이터 갱신
		if(curHour.equals("8") && preHour.equals("7")) {
			systemAnalysis.algorismImpl();
		}
	
		//현재 시간을 preHour flag 에 저장
		preHour = curHour;
		
		Thread.sleep(1000 * 60 * 1); //1분의 대기 - 반복적인 crawling 에 대한 우려
	}
}
