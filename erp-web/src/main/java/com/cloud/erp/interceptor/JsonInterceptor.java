package com.cloud.erp.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 
 * @author Bollen
 *
 */
public class JsonInterceptor implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		return invocation.invoke();
	}

}
