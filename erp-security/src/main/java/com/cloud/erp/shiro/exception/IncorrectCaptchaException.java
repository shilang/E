/**
 * @Title:  IncorrectCaptchaException.java
 * @Package:  com.cloud.erp.shiro.exception
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  下午4:53:33
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @ClassName  IncorrectCaptchaException
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  下午4:53:33
 *
 */
public class IncorrectCaptchaException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public IncorrectCaptchaException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IncorrectCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public IncorrectCaptchaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public IncorrectCaptchaException(Throwable cause) {
		super(cause);
	}
	
}
