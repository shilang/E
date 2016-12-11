package com.cloud.erp.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloud.erp.utils.Constants;

public class SessionFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	private boolean isContextPath(String url, String contextPath){
		if(url.endsWith("/")){
			url = url.substring(0, url.length() - 1);
		}
		if(url.equals(contextPath)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		
		String url = httpServletRequest.getRequestURI();
		String contextPath = httpServletRequest.getContextPath();
		String loginPage = "/login.action";
		
		//login url
		String loginUrl = contextPath + loginPage;
		
		//session timeout
		if(session == null || session.getAttribute(Constants.SHIRO_USER) == null){
			if(url != null && (url.endsWith(loginPage) || url.endsWith("index.jsp") 
					|| isContextPath(url, contextPath) || url.contains("web-static"))){
				chain.doFilter(httpServletRequest, httpServletResponse);
			}else{
				String str = "";
				if(httpServletRequest.getHeader("x-requested-with") != null &&
						httpServletRequest.getHeader("x-requested-with")
						.equalsIgnoreCase("XMLHttpRequest")){
					httpServletResponse.addHeader("sessionstatus", "timeOut");
					httpServletResponse.addHeader("loginPath", loginUrl);
					//chain.doFilter(httpServletRequest, httpServletResponse);
					str = "{msg:'timeOut'}";
				}else {
					str = "<script language='javascript'>alert('会话过期,请重新登录!');"
	                        + "window.top.location.href='"
	                        + loginUrl
	                        + "';</script>";
					httpServletResponse.setContentType("text/html;charset=UTF-8");
				}
				
				try{
					PrintWriter writer = httpServletResponse.getWriter();
					writer.write(str);
					writer.flush();
					writer.close();
				}catch(Exception e){
					if(log.isInfoEnabled()){
						log.info(e.getMessage());
					}
				}
				
			}
		}else{
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public void destroy() {

	}

}
