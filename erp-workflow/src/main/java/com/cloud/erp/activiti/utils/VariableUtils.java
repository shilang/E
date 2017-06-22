package com.cloud.erp.activiti.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Bollen
 *
 */
public class VariableUtils {
	
	private static final Logger log = LoggerFactory.getLogger(VariableUtils.class);

	public static List<String> getFieldNames(Object model){
		List<String> strFields = new ArrayList<String>();
		Field[] fields = model.getClass().getDeclaredFields();
		String fieldName = null;
		for(Field field : fields){
			fieldName = field.getName();
			if(("serialVersionUID").equals(fieldName)){
				continue;
			}
			strFields.add(fieldName);
		}
		return strFields;
	}
	
	public static Map<String, String> getProperties(Object model){
		Map<String, String> properties = new HashMap<String, String>();
		List<String> fieldNames = getFieldNames(model);
		for(String fieldName : fieldNames){
			try {
				Method method = model.getClass().getMethod(getMethodName(fieldName));
	    		String value = (String) method.invoke(model);
	    		if(value != null && !"".equals(value)){
	    			properties.put(fieldName, value);
	    		}
			} catch (Exception e) {
				if(log.isDebugEnabled()){
					log.debug("get method error from {}", model.toString());
				}
			}
		}
		return properties;
	}
	
	private static String getMethodName(String field){
		return "get" + field.substring(0,1).toUpperCase() + field.substring(1);
	}

}
