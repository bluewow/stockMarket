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
	
	public void algorismImpl() throws IOException {
		List<KoreaStocks> stocks = new ArrayList<>();
		List<Analysis> analysisList = new ArrayList<>();
		List<String> codeNum = new ArrayList<>();
		int result = 0; 
		int influence = 0;
		
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); //날짜정보를 가져온다
//		System.out.println(date.format(System.currentTimeMillis()));
		
		stocks = koreaStockDao.getList();	//종목코드, 이름을 가져온다
		influence = influence();
//		for(KoreaStocks stock : stocks) {
			Analysis analysis = new Analysis();
			
//			String code = stock.getStockCode();
			String code = "095660"; //temp
			
//			analysis.setCodeNum(code);
//			analysis.setRecord_date(date.format(System.currentTimeMillis()));
			analysis.setTrend((int)(trend("네오위즈") * 0.2));
//			analysis.setSupply((int)(supply(code) * 0.25));
//			analysis.setScale((int)(scale(code) * 0.20));
			analysis.setContents((int)(contents("네오위즈") * 0.25));
//			analysis.setInfluence((int)(influence * 0.1));
//			analysis.calculateResultValue();
			
			//Analysis 결과를 entity 에 저장하여 최종 DB 로 넘겨준다
			analysisList.add(analysis);
//		}
			
		System.out.println(analysisList.toString());
//		analysisDao.insert(analysisList);
	}
	
	private int supply(String code) {

		return 0;
	}
	
	private int scale(String code) {
		return 0;
	}
	
	private int influence() {
		//코스피 코스닥 동향
		return 0;
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
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
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
		String month = doc.select(".title_desc.all_my").text().replace("1-10 / ","").replace(",","").replace("건","");
		
		url = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=" + name + "&sm=tab_opt&sort=0&photo=0&field=0&reporter_article=&pd=4";
		doc = crawling(url);
		String oneDay = doc.select(".title_desc.all_my").text().replace("1-10 / ","").replace(",","").replace("건","");
		
		//한달기준 1일분량의 기사 검색 (세분화 필요)
		//  0점   	0  ~   2%
		// 20점	  	3% ~   6%
		// 40점		7% ~  12%
		// 60점	   13% ~  23%
		// 80점	   24% ~  50%
		//100점	   60% ~ 100%
		
		double result = Double.parseDouble(oneDay) / Double.parseDouble(month);
		result = result * 100;
//		System.out.println(result); //for debugging
		
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
			doc = Jsoup.connect(url).ignoreContentType(true).timeout(60000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
