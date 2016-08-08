/**
 * @Title:  FormLoginFilter.java
 * @Package:  com.cloud.erp.shiro.filter
 * @Description:  
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

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.cloud.erp.utils.Constants;

/**
 * @ClassName  FormLoginFilter
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月10日  下午3:20:50
 *
 */
public class LoginFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		if(isLoginRequest(request, response)){
			if(isLoginSubmission(request, response)){
				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
				String captcha = (String) httpServletRequest.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
				String input = httpServletRequest.getParameter(Constants.CAPTCHA);
				if(null != input && null != captcha){
					if(!input.equalsIgnoreCase(captcha)){
						httpServletRequest.setAttribute("shiroLoginFailure", Constants.CAPTCHA_ERR);
						return true;
					}
				}
			}
		}
		return super.onAccessDenied(request, response);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		loginLogging();
		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		return super.onLoginFailure(token, e, request, response);
	}
	
	public void loginLogging(){
		//new BLogger().write("登录系统", "成功");
	}
	
}
