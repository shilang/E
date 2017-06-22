package com.cloud.erp.interceptor;

import com.cloud.erp.exception.BusinessExceptionHandler;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 
 * @author Bollen
 *
 */
public class ExceptionInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (RuntimeException e) {
			return BusinessExceptionHandler.handler(e, invocation);
		}
	}

}
