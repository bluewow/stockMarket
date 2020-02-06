package com.stockmarket.www.service.basic;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.dao.AnalysisDao;
import com.stockmarket.www.dao.KoreaStocksDao;
import com.stockmarket.www.dao.StockDetailDao;
import com.stockmarket.www.entity.Analysis;
import com.stockmarket.www.entity.KoreaStocks;
import com.stockmarket.www.entity.StockDetail;

@Service
public class BasicSystemAnalysis {
	private static String clientId = "qcb_heUiOwEgQEvETpk8";// 애플리케이션 클라이언트 아이디값";;
	private static String clientSecret = "HopZWjJ6Qf";		// 애플리케이션 클라이언트 시크릿값";
	private static String apiURL = "https://openapi.naver.com/v1/datalab/search";
	private static final int INDEX_ZERO = 0;	//네이버 트렌드 key 값에 대한 반환
	
	@Autowired
	KoreaStocksDao koreaStockDao;
	
	@Autowired
	StockDetailDao stockDeatailDao;
	
	@Autowired
	AnalysisDao analysisDao;
	
	public Analysis getAnalysisResult(String codeNum) {
		return analysisDao.get(codeNum);
	}
	public void algorismImpl() throws IOException {
		List<KoreaStocks> stocks = new ArrayList<>();
		List<Analysis> analysisList = new ArrayList<>();
		List<String> codeNum = new ArrayList<>();
		int influence = 0;
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); //날짜정보를 가져온다
//		System.out.println(date.format(System.currentTimeMillis()));
		
		stocks = koreaStockDao.getList();	//종목코드, 이름을 가져온다
		influence = influence();
		int cnt = 0;
		for(KoreaStocks stock : stocks) {
			Analysis analysis = new Analysis();
			String code = stock.getStockCode();
			String company = stock.getCompanyName();
			
			analysis.setCodeNum(code);
			analysis.setRecord_date(date.format(System.currentTimeMillis()));
			if(analysisDao.get(code) != null && analysisDao.get(code).getTrend() != -1) {//일일 1000회 limit 로 인한 workaround
				int trend = trend(company);
				if(trend == -1)
					analysis.setTrend(-1);
				else 
					analysis.setTrend((int)(trend * 0.2));
			}
			analysis.setSupply((int)(supply(code) * 0.20));
			analysis.setScale((int)(volume(code) * 0.15));	//tradeVolume
			analysis.setContents((int)(contents(company) * 0.30));
			analysis.setInfluence(influence);
//			analysis.setInfluence((int)(influence * 0.15));
			analysis.calculateResultValue();
			
			//1) Analysis 결과를 entity 에 저장하여 최종 DB 로 넘겨준다
//			analysisList.add(analysis);
			
			//2) 즉시 DB 에 upload 한다
			analysisDao.insert(analysis);
			cnt++;
			System.out.println("분석..." + cnt);
		}
			
//		analysisDao.insertAll(analysisList);
		System.out.println("analysisList upload success");
	}
	
	private int supply(String code) {
		List<StockDetail> stocks = stockDeatailDao.get(code);
		int day10 = 0;
		int day100 = 0;
		
		int cnt = 0;
		//개인의 동향을 파악한다 10일 / 100일
		for(StockDetail stock : stocks) {
			int data = stock.getIndi_pure_buy_quant();
			if(data <= 0)
				data = -data;
				
			if(cnt <= 10) 
				day10 += data;
			
			day100 += data;
			cnt++;
		}

		double result = (double)day10 / day100;
		result = result * 100;
		
		if(Double.isNaN(result))
			result = 0;
		
//		System.out.println("day10 : " + day10 + " day100 : " + day100 + " result : " + result);
		//100기준 10일 분량의 개인 동향 분석 
				//  0점   	0  ~   10%
				// 20점	  	11% ~  20%
				// 40점		21% ~  30%
				// 60점	    31% ~  45%
				// 80점	    46% ~  70%
				//100점	    71% ~ 100%
		
		if(result <= 10)
			return 0;
		else if(result <= 20)
			return 20;
		else if(result <= 30)
			return 40;
		else if(result <= 45)
			return 60;
		else if(result <= 75)
			return 80;
		else 
			return 100;
		
	}
	 
	private int volume(String code) {
		List<StockDetail> stocks = stockDeatailDao.get(code);
		long avgTradeVolume = 0;
		int lastTradeVolume = 0;
		
		int cnt = 0;
		//평균 거래치 증가율을 판단한다
		for(StockDetail stock : stocks) {
			if(cnt == 0 )
				lastTradeVolume = stock.getAcc_quant();
			
			if(cnt > 15)
				break;
			
			avgTradeVolume += stock.getAcc_quant();
			cnt++;
		}

//		System.out.println("avg : " + avgTradeVolume/15 + "tradeVolume : " + lastTradeVolume);
		double result = lastTradeVolume / ((double)avgTradeVolume/15) * 100;
		result = result - 100;
		if(Double.isNaN(result))
			result = 0;

		//15일기준  전날 거래량 분석 
		// 0점   		< 0%
		// 20점	  	1% ~  15%
		// 40점		16% ~  30%
		// 60점	    31% ~  45%
		// 80점	    46% ~  70%
		//100점	    71% ~ 100%
		
		if(result <= 0)
			return 0;
		else if(result <= 15)
			return 20;
		else if(result <= 30)
			return 40;
		else if(result <= 45)
			return 60;
		else if(result <= 75)
			return 80;
		else 
			return 100;
	}
	
	private int influence() {
		//코스피 코스닥 동향
		return 50; 
	}
	
	private int trend(String name) throws IOException {
		int value = 0;
		
		//오늘날짜
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(System.currentTimeMillis());
		//한달전 날짜
		Calendar mon = Calendar.getInstance();
		mon.add(Calendar.MONTH , -1);
		String beforeMonth = new java.text.SimpleDateFormat("yyyy-MM-dd").format(mon.getTime());
		
		String json = "{\"startDate\":\"" + beforeMonth + "\",\"endDate\":\"" + today + "\""
				+ ",\"timeUnit\":\"date\","
				+ "\"keywordGroups\":[{\"groupName\":\"" + name + "\",\"keywords\":[\"" + name + "\",\"" + name + "\"]}"
				+ "]}";
		byte[] body = json.getBytes();
		
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);

		//요청
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(body);
		wr.flush();
		wr.close();
		
		BufferedReader br;
		if (con.getResponseCode() == 200) { // 정상 호출
			System.out.println("success");
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
			return -1;	//1000 회 조회를 넘긴 상황
		}

		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		
		try {
			JSONObject result;
			result = (JSONObject) new JSONParser().parse(response.toString());
			JSONArray arrayStep1 = (JSONArray) result.get("results");
			JSONObject objStep1 = (JSONObject) arrayStep1.get(INDEX_ZERO);
			JSONArray arrayStep2 = (JSONArray) objStep1.get("data");
			JSONObject obj = (JSONObject) arrayStep2.get(arrayStep2.size()-1);
			value = Math.round(Float.parseFloat(obj.get("ratio").toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	private int contents(String name) {
		Document doc = null;
		
		String url = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=" + name + "&sm=tab_opt&sort=0&photo=0&field=0&reporter_article=&pd=2";
		doc = crawling(url);
		String month = doc.select(".title_desc.all_my").text().replaceAll("[\\d]+[-][\\d]+","").replace("/","").replace(",","").replace("건","");
		
		url = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=" + name + "&sm=tab_opt&sort=0&photo=0&field=0&reporter_article=&pd=4";
		doc = crawling(url);
		String oneDay = doc.select(".title_desc.all_my").text().replaceAll("[\\d]+[-][\\d]+","").replace("/","").replace(",","").replace("건","");
		//한달기준 1일분량의 기사 검색 (세분화 필요)
		//  0점   	0  ~   2%
		// 20점	  	3% ~   6%
		// 40점		7% ~  12%
		// 60점	   13% ~  23%
		// 80점	   24% ~  50%
		//100점	   60% ~ 100%
		
		if(oneDay.equals(""))
			oneDay = "1";
		
		if(month.equals(""))
			month = "1";
		
//		System.out.println("oneDay : " + oneDay + " month : " + month);  
		double result = Double.parseDouble(oneDay) / Double.parseDouble(month);
		result = result * 100;
		
		if(result <= 2)
			return 0;
		else if(result <= 6)
			return 20;
		else if(result <= 12)
			return 40;
		else if(result <= 23)
			return 60;
		else if(result <= 50)
			return 80;
		else 
			return 100;
	}

	private Document crawling(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).ignoreContentType(true).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(doc == null) {	//null 인경우가 발생중이다. workaround 처리로 1회 더 시도해보자
			System.out.println("doc is null");
			try {
				doc = Jsoup.connect(url).ignoreContentType(true).timeout(10000).get();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return doc;
	}
}
