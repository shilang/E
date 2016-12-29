package com.cloud.erp.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reflect {

	private static final Logger log = LoggerFactory.getLogger(Reflect.class);
	private static final String GET = "get";
	private static final String SET = "set";

	public static Object invokeSetMethod(Object object, String methodName, Object param) {
		if(null == param)
		{
			return null;
		}
		return invokeSetMethodAllowNull(object, methodName, param.getClass(), param);
	}
	
	/**
	 * allow null for method parameter.
	 * @param object
	 * @param methodName
	 * @param paramType
	 * @param param
	 * @return
	 */
	public static Object invokeSetMethodAllowNull(Object object, String methodName, 
			Class<?> paramType, Object param) {
		try {
			Method method = object.getClass().getMethod(generateSetMethodName(methodName), paramType);
			return method.invoke(object, param);
		} catch (Exception e) {
			if (log.isInfoEnabled()) {
				log.info("Failed to invoke set method");
			}
		}
		return null;
	}

	public static Object invokeGetMethod(Object object, String methodName) {
		try {
			Method method = object.getClass().getMethod(generateGetMethodName(methodName));
			return method.invoke(object);
		} catch (Exception e) {
			if (log.isInfoEnabled()) {
				log.info("Failed to invoke get method");
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static List invokeQueryMethod(Object object, String methodName, 
			Map<String, Object> params, PageUtil pageUtil){
		try {
			Method method = object.getClass().getMethod(methodName, Map.class, pageUtil.getClass());
			return (List) method.invoke(object, params, pageUtil);
		} catch (Exception e) {
			if(log.isInfoEnabled()){
				log.info("Failed to invoke invokeQueryMethod");
			}
		}
		return null;
	}

	private static String generateSetMethodName(String methodName) {
		return SET + methodName.substring(0, 1).toUpperCase()
				+ methodName.substring(1);
	}

	private static String generateGetMethodName(String methodName) {
		return GET + methodName.substring(0, 1).toUpperCase()
				+ methodName.substring(1);
	}
}
