package com.cloud.erp.exception;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;

import com.cloud.erp.service.exception.BusinessStatusLimitedException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * @author Bollen
 *
 */
public class BusinessExceptionHandler {

	private static final String RESULT = "error";
	private static final String VALUE_EXISTS = "字段内容已存在.";
	
	private static ActionContext getActionContext(ActionInvocation invocation){
		return invocation.getInvocationContext();
	}
	
	public static String handler(RuntimeException e, ActionInvocation invocation){
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		if(e instanceof ConstraintViolationException){
			parameterMap.put(RESULT, VALUE_EXISTS);
		}else if(e instanceof BusinessStatusLimitedException){
			parameterMap.put(RESULT, e.getMessage());
		}
		getActionContext(invocation).setParameters(parameterMap);
		return RESULT;
}
}
