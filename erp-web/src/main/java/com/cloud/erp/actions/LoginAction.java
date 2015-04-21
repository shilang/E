/**
 * @Title:  LoginAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月4日  下午4:55:28
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.exceptions.IncorrectCaptchaException;
import com.cloud.erp.service.LoginService;
import com.cloud.erp.shiro.token.CaptchaUsernamePasswordToken;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName  LoginAction
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月4日  下午4:55:28
 *
 */

@Action(value = "loginAction")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String captcha;
	private String userMacAddr;
	private String userKey;
	
	@Autowired
	private LoginService loginService;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getUserMacAddr() {
		return userMacAddr;
	}

	public void setUserMacAddr(String userMacAddr) {
		this.userMacAddr = userMacAddr;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String load() throws Exception{
		Subject subject = SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		token.setCaptcha(captcha);
		token.setRememberMe(true);
		
		Json json = new Json();
		json.setTitle("登录提示");
		
		try {
			subject.login(token);
			json.setStatus(true);
		} catch (UnknownSessionException use) {
			// TODO: handle exception
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			// TODO: handle exception
			json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			// TODO: handle exception
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		}catch (LockedAccountException lae) {
			// TODO: handle exception
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		}catch (IncorrectCaptchaException ie) {
			// TODO: handle exception
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		}catch (AuthenticationException ae) {
			// TODO: handle exception
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		}catch (Exception e) {
			// TODO: handle exception
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		
		return null;
	}
	
	/**
	 * 
	 * function: TODO user logout
	 *
	 * @Title:  logout
	 * @param: 
	 * @return:  String
	 * @throws:
	 */
	public String logout() throws Exception{
		SecurityUtils.getSubject().logout();
		Json json = new Json();
		json.setStatus(true);
		OutputJson(json);
		return null;
	}
	
	/**
	 * 
	 * function: TODO search all menu for the user
	 *
	 * @Title:  findAllFunctionList
	 * @param: 
	 * @return:  String
	 * @throws:
	 */
	public String findAllFunctionList() throws Exception{
		OutputJson(loginService.findMenuList());
		return null;
	}

}
