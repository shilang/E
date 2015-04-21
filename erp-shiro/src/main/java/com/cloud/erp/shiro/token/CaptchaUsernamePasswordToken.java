/**
 * @Title:  CaptchaUsernamePasswordToken.java
 * @Package:  com.cloud.erp.shiro.token
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  下午4:22:27
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName  CaptchaUsernamePasswordToken
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  下午4:22:27
 *
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	private String captcha;
	
	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}
	
	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe,
			String host, String captcha){
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}
	
	public CaptchaUsernamePasswordToken(){
		
	}
}
