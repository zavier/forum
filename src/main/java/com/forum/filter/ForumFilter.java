package com.forum.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ForumFilter implements Filter {

	//需要登录才可以访问的url资源
	private static final String[] DO_FILTER_URL = {"addBoardPage", "addTopicPage", 
			"updateBoardPage"};
	
	public void destroy() {

	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean filter = false;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = httpRequest.getRequestURI();
		for (String string : DO_FILTER_URL) {
			if(uri.indexOf(string) != -1){
				filter = true;
			}
		}
		if(filter){
			Object object = httpRequest.getSession().getAttribute("LOGINUSER");
			if(null == object){
				request.getRequestDispatcher("/login/loginPage").forward(request, response);
				return ;
			}
		}
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
