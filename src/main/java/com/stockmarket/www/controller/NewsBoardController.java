package com.stockmarket.www.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsBoardController {
	
	@GetMapping("/card/board/news_board")
	public String newsBoard(Model model) throws IOException   {
		
		String url = "https://finance.naver.com/news/mainnews.nhn";
		Document doc = null;

		List<Map<String, Object>> newsList = new ArrayList<>();

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("ul.newsList");
		Elements html = doc.select("ul.newsList a");

		Iterator<Element> ie1 = element.select(".articleSubject").iterator();
		Iterator<Element> ie2 = element.select(".articleSummary").iterator();

		int cnt = 0;
		while (ie1.hasNext()) {
			Map<String, Object> newscontent = new HashMap();
			newscontent.put("subject", ie1.next().text());
			newscontent.put("summary", ie2.next().text());
			newscontent.put("url", "https://finance.naver.com" + html.get(cnt).attr("href") + "\n");
			cnt++;
 
			newsList.add(newscontent); 

		}
		model.addAttribute("news_lists", newsList);
		
	
		
		return "newsBoard";
	}

}
