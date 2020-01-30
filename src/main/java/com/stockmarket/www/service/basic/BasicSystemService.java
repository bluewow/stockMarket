package com.stockmarket.www.service.basic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.KoreaStocksDao;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.RecordAssetDao;
import com.stockmarket.www.dao.StockDetailDao;
import com.stockmarket.www.dao.UpjongDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.entity.KoreaStocks;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.entity.StockDetail;
import com.stockmarket.www.entity.Upjong;
import com.stockmarket.www.service.SystemService;
import org.jsoup.Connection;

@Service
public class BasicSystemService implements SystemService{
	// <th> 회사명|종목코드|업종|주요제품|상장일|결산월|대표자명|홈페이지|지역 </th>
	private static final int COMPANY_INFO_COLUMN = 9;
	private List<String[]> companyList;
	private String[] dataBuffer;
	private KoreaStocks koreaStocks;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private HaveStockDao haveStockDao;
	
	@Autowired
	private RecordAssetDao recordAssetDao;
	
	@Autowired
	private StockDetailDao stockDetailDao;
	
	@Autowired
	private UpjongDao upjongDao;
	
	@Autowired
	private KoreaStocksDao koreaStocksDao;

	public BasicSystemService() {

	}

	/*-------------------------- refreshStockPrice ----------------------------*/
	public void refreshStockPrice() {
		List<KoreaStocks> stocks = new ArrayList<>();
		List<String> codeNum = new ArrayList<>();
		List<CurStock> stockMarket;

		// DB 를 참조하여 KOSPI, KOSDAQ 모든 종목에 대한 종목코드를 가져온다
		stocks = koreaStocksDao.getList();
		for(KoreaStocks entity : stocks) {
			codeNum.add(entity.getStockCode());
		}

		try {
			getCurrentStockPrice(codeNum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getCurrentStockPrice(List<String> codeNums) throws InterruptedException {
		Document doc = null;
	
		for (String codeNum : codeNums) {
			String url = "https://finance.naver.com/item/main.nhn?code=" + codeNum;
			try {
				doc = Jsoup.connect(url).ignoreContentType(true).timeout(60000).get();
			} catch (IOException e) {
//				AppContext.setLog("네이버 금융 크롤링도중 IOException 발생", BasicSystemService.class.getName());
				e.printStackTrace();
				continue;
			}
			if(doc == null) 
				continue; 
			
			if(doc.text().contains("동시에 접속하는 이용자 수가 많거나 인터넷 네트워크 상태가 불안정하여 현재 웹페이지의 접속이 불가합니다")) {
				continue;	//TODO 해당 codeNum이 검색이 되지 않는 경우
			}
			
			// 현재가, 상태(상승 or 하락), 금액, +/-, percent 를 가져오는 CSS query 문
			Elements status = doc.select(".no_today span:eq(0), .no_exday em span:lt(2)");
			// 호가창 데이터
			Elements trade = doc.select("#tab_con2");
			if (status == null || trade == null) {
				System.out.println("status, trade is null" + "codeNums : " + codeNums);
//				AppContext.setLog("네이버 금융 크롤링 데이터가 null 일 경우", BasicSystemService.class.getName());
				continue;
			}
			
			Map<Integer, Integer> sell = new LinkedHashMap();
			Map<Integer, Integer> buy = new LinkedHashMap();
			if(trade.text().length() >100) { //거래정지된 목록의 호가창을 배제하기 위해서... TODO 다른 방법을 찾기
				String buffer = trade.select(".f_down").text().trim().replace(",", "");
				String buffersDown[] = buffer.split(" ");
				for(int i = 0; i < buffersDown.length - 1; i=i+2) {
					if(!buffersDown[i].equals("") && !buffersDown[i+1].equals(""))
						sell.put(Integer.parseInt(buffersDown[i+1]), Integer.parseInt(buffersDown[i]));
				}
				buffer = trade.select(".f_up").text().trim().replace(",", "");
				String buffersUp[] = buffer.split(" ");
				for(int i = 0; i < buffersUp.length - 1; i=i+2) {
					if(!buffersUp[i].equals("") && !buffersUp[i+1].equals(""))
						buy.put(Integer.parseInt(buffersUp[i]), Integer.parseInt(buffersUp[i+1]));
				}
			}
			
			CurStock curStockInfo = new CurStock();
			AppContext.getStockMarket().put(codeNum, curStockInfo.parser(codeNum + " " + status.text(), sell, buy));
//			System.out.println(curStockInfo.toString()); //for debugging
			Thread.sleep(10);
		}
	}

	/*-------------------------- update Market ----------------------------*/
	@Override
	public boolean updateMarket(String market) {
		Document doc = null;
		companyList = new ArrayList<String[]>();
		dataBuffer = new String[COMPANY_INFO_COLUMN];
		String type = null;

		if (market.equals("KOSPI"))
			type = "stockMkt";

		if (market.equals("KOSDAQ"))
			type = "kosdaqMkt";

		String url = "http://kind.krx.co.kr/corpgeneral/corpList.do" + "?method=download" + "&searchType=13"
				+ "&orderMode=3" + "&orderStat=D" + "&marketType=" + type; // stockMkt 코스피 kosdaqMkt 코스닥

		try {
			doc = Jsoup.connect(url).ignoreContentType(true).timeout(5000).post();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		// tr Tag 이하를 선택
		Elements contents = doc.select("tr");
		if (contents == null) {
			return false;
		}

		// 반복되는 th, td tag 로 sorting 한다
		//write(contents, "th");
		write(contents, "td");

		return true;
	}

	private void write(Elements contents, String tag) {
		int cnt = 0;
		List<KoreaStocks> koreaList = new ArrayList<>();
		for (Element element : contents.select(tag)) {
			dataBuffer[cnt] = element.text();
			cnt++;
			if (cnt % COMPANY_INFO_COLUMN == 0) {
				cnt = 0;
				String[] data = new String[COMPANY_INFO_COLUMN];
				for (int i = 0; i < data.length; i++) {
					data[i] = dataBuffer[i];

				}
				companyList.add(data);
				
				koreaStocks = new KoreaStocks();
                koreaStocks.setCompanyName(data[0]);
                koreaStocks.setStockCode(data[1]);
                koreaStocks.setSectors(data[2]);
                koreaStocks.setMainProduct(data[3]);
                koreaStocks.setStockedDay(data[4]);
                koreaStocks.setSettlementMonth(data[5]);
                koreaStocks.setRepresentativeName(data[6]);
                koreaStocks.setWebsite(data[7]);
                koreaStocks.setLocation(data[8]);
                
                koreaList.add(koreaStocks);
				
				
			}
		}
		
		koreaStocksDao.delete();
		koreaStocksDao.insert(koreaList);
		koreaStocksDao.update("KT","케이티");
	}
	/*-------------------------- insert Asset Record ----------------------------*/

	@Override
	public int insertRecordAsset() {
		int value = 0;
		int result = 0;
		SimpleDateFormat date = new SimpleDateFormat("YYMMdd");
		String regdate = date.format(System.currentTimeMillis());
		System.out.println(regdate);

		List<Member> memberList = new ArrayList<>();
		memberList.addAll(memberDao.getMemberList());
		for (Member memberData : memberList) {
			int memberId = memberData.getId();
			List<HaveStockView> list = new ArrayList<>();
			list.addAll(haveStockDao.getList(memberId));
			for (HaveStockView data : list) {
				// (보유종목당 현재가 및 보유수량 확인용)
				// System.out.println(data.getPrice()+","+data.getQuantity());
				value += Integer.parseInt(data.getPrice().replaceAll(",", "")) * data.getQuantity();
				result += recordAssetDao.insert(new RecordAsset(memberId, regdate, value));
				System.out.println("result:"+result);
				break;
			}
		}
		return result;
	}

	public void setStockDataAll(String codeNum) {
		Gson gson = new Gson();

		// 일별시세 게시판
		String url = "https://m.stock.naver.com/api/item/getTrendList.nhn?code=" + codeNum + "&size=1000";
		Document doc = naverCrawling(url);

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(doc.text());
		String values = jsonElement.getAsJsonObject().get("result").toString();

		// 크롤링 데이터를 객체에 저장
		StockDetail[] stockDetail = gson.fromJson(values, StockDetail[].class);
		for (StockDetail obj : stockDetail) {
			System.out.println(obj);
		}
		stockDetailDao.insert(stockDetail);
		stockDetailDao.deletePreDate();
//		System.out.println("END");

	}

	public List<StockDetail> getStockDetail(String codeNum) {
		return stockDetailDao.get(codeNum);
	}

	public void upjongCrawling() {

		String upjongUrl = "https://finance.naver.com/sise/sise_group.nhn?type=upjong";
		Document doc = null;

		try {
			doc = Jsoup.connect(upjongUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// tr tag에 업종 링크를 선택

		Elements Industrytable = doc.select("#contentarea_left");

		Iterator<Element> IndustryAtag = Industrytable.select("tr a").iterator();
		Iterator<Element> IndustryName = Industrytable.select("tr a").iterator();
		// IndustryAtag.next().attr("href") => a 링크만 뽑아냄
		// IndustryAtag.next() => 업종 명만 뽑아냄
		ArrayList<String> upjongAtag = new ArrayList<>();
		ArrayList<String> upjonName = new ArrayList<>();

		// 1. 업종명과 해당링크를 얻는다.
		while (IndustryAtag.hasNext())
			upjongAtag.add(IndustryAtag.next().attr("href"));

		// 2. 업종명에 해당하는 링크를 타고 들어가서 상세 종목명을 얻는다.
		while (IndustryName.hasNext())
			upjonName.add(IndustryName.next().text().trim());

		// 2차 작업 - 업종과 주식종목을 매칭
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<Upjong> upjongList = new ArrayList<>();

		int totalCnt = 0;
		for (int i = 0; i < upjongAtag.size(); i++) {
			List<String> list = new ArrayList<String>();
			String url = "https://finance.naver.com" + upjongAtag.get(i);

			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Elements companyList = doc.select("tbody a");
			String detailCompanyList = companyList.select("a").text().trim();
			String[] companyArray = detailCompanyList.split("  ");

			for (String string : companyArray) {
				list.add(string);
			}
			map.put(upjonName.get(i), list);
		} // -업종명 넣는작업

		List<String> getData = new ArrayList<String>();
		for (String k : map.keySet()) { // 업종
			getData = map.get(k);
			for (String j : getData) { // 한 업종내의 종목들
				Upjong upjong = new Upjong(k, j);
				upjongList.add(upjong);
				totalCnt++;
//            	   System.out.println(k + " : " + j); //for debugging
			}
//			System.out.println(k); //for debugging
		}
		upjongDao.insert(upjongList);
		System.out.println("end" + totalCnt);
	}
	
	//네이버 크롤링 GET 방식 LIB 
	private static Document naverCrawling(String url) {
		Document doc = null;	//크롤링 결과를 담는 Document
		Response response = null; //jsoup connect 결과 반환 
		
		try {
			response = Jsoup.connect(url)
					.ignoreContentType(true)
					.method(Connection.Method.GET)
					.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			doc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
		
}
