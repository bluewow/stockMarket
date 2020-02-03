package com.stockmarket.www.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
@WebFilter("/*")
public class AuthenticationFilter implements Filter{
	
	private static final String[] noAuthUrls = {
			//TODO
			"/card/company/list",
			"/card/trade/analysis",
			"/card/trade/analysisUpdate", 
			"/card/board/news_board",
			};
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String urlPath = httpRequest.getServletPath();
		boolean filterPass = false;
		
		if(session.getAttribute("id") == null) {
			//로그인 이전 권한 세팅
			for(String url : noAuthUrls) {
				// 1. /card 외에는 bypass
				// 2. noAuthUrls path 는 bypass
				if(!urlPath.substring(0, 5).equals("/card") || url.equals(urlPath)) {
					filterPass = true;
					break;
				}
			}
			
			if(filterPass == true) 
				chain.doFilter(request, response);
			else  {
//				System.out.println(urlPath);
				httpRequest.getRequestDispatcher("/card/error").forward(httpRequest, httpResponse);
			}
		} else {
			//로그인 이후 No filter for auth 
			chain.doFilter(request, response);
		}
		
	}

}
