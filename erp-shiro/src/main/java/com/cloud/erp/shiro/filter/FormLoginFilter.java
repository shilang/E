/**
 * @Title:  FormLoginFilter.java
 * @Package:  com.cloud.erp.shiro.filter
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月10日  下午3:20:50
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * @ClassName  FormLoginFilter
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月10日  下午3:20:50
 *
 */
public class FormLoginFilter extends PathMatchingFilter {

	private static final String DEFAULT_LOGIN_URL = "/login.jsp";
	private static final String DEFAULT_SUCCESS_URL = "/";
	
	private String loginUrlParam = DEFAULT_LOGIN_URL;
	private String successUrlParam = DEFAULT_SUCCESS_URL;
	
	/**
	 * @param loginUrlParam the loginUrlParam to set
	 */
	public void setLoginUrlParam(String loginUrlParam) {
		this.loginUrlParam = loginUrlParam;
	}
	
	/**
	 * @param successUrlParam the successUrlParam to set
	 */
	public void setSuccessUrlParam(String successUrlParam) {
		this.successUrlParam = successUrlParam;
	}
	
	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if(SecurityUtils.getSubject().isAuthenticated()){
			redirectToSuccessUrl(req, resp);
		}else {
			saveRequestAndRedirectToLogin(req, resp);
		}
		
		return true;
	}	
	
	private boolean redirectToSuccessUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		WebUtils.redirectToSavedRequest(request, response, successUrlParam);
		return false;
	}
	
	private void saveRequestAndRedirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		WebUtils.saveRequest(request);
		WebUtils.issueRedirect(request, response, loginUrlParam);
	}
	
}
