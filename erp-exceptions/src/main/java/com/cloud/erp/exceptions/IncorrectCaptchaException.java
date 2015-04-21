/**
 * @Title:  IncorrectCaptchaException.java
 * @Package:  com.cloud.erp.shiro.exception
 * @Description:  TODO
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
package com.cloud.erp.exceptions;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @ClassName  IncorrectCaptchaException
 * @Description  TODO
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IncorrectCaptchaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public IncorrectCaptchaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public IncorrectCaptchaException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
