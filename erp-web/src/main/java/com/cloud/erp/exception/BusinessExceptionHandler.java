package com.cloud.erp.exception;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;

import com.opensymphony.xwork2.ActionInvocation;

public class BusinessExceptionHandler {

	private static final String RESULT = "error";
	private static final String VALUE_EXISTS = "字段内容已存在.";
	
	public static String handler(RuntimeException e, ActionInvocation invocations){
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		if(e instanceof ConstraintViolationException){
			parameterMap.put(RESULT, VALUE_EXISTS);
			invocations.getInvocationContext().setParameters(parameterMap);
		}
		
		return RESULT;
}
}
