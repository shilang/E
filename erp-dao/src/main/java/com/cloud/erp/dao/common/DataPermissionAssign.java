package com.cloud.erp.dao.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloud.erp.entities.datafilter.FilterGroup;
import com.cloud.erp.entities.datafilter.FilterParam;
import com.cloud.erp.entities.datafilter.FilterTranslator;
import com.cloud.erp.entities.sys.RObject;

public abstract class DataPermissionAssign{
	
	private static final Logger logger = LoggerFactory.getLogger(DataPermissionAssign.class);
	
	public abstract void RegCurrentParmMatch();
	
	public abstract FilterGroup getRule(String moduleName);
	
	private Map<String, List<FilterParam>> getCommandAndParams(String moduleName){
		FilterGroup filterGroup = getRule(moduleName);
		Map<String, List<FilterParam>> filter = new HashMap<String, List<FilterParam>>();
		FilterTranslator filterTranslator = new FilterTranslator();
		filterTranslator.setGroup(filterGroup);
		filterTranslator.Translate();
		String cmd = filterTranslator.getCommandText();
		List<FilterParam> params = filterTranslator.getParams();
		filter.put(cmd, params);
		return filter;
	}
	
	private Map<String, Object> analyzeParam(List<FilterParam> filterParams){
		Map<String, Object> params = new HashMap<String, Object>();
		String name = null;
		Object value = null;
    	for(FilterParam filterParam : filterParams){
    		name = FilterTranslator.paramPrefixToken + filterParam.getName();
    		value =  filterParam.getValue();
    		if(value instanceof RObject){
    			RObject obj = (RObject) value;
    			try {
    				Method method = obj.getMethod();
    				Object o = obj.getObj();
					value = method.invoke(o);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					if(logger.isDebugEnabled()){
						logger.debug("analyzeParam error in DataPermissionAssign");
					}
					return null;
				}
    		}
    		
    		params.put(name, value);
    	}
    	return params;
	}
	
    private String applyParams(String moduleName){
    	Map<String, List<FilterParam>> cmdAndParams = getCommandAndParams(moduleName);
    	String where = cmdAndParams.keySet().iterator().next();
    	Map<String, Object> params = analyzeParam(cmdAndParams.values().iterator().next());
		for(String key : params.keySet()){
			String value = params.get(key).toString();
			where = where.replace(key, value);
		}
		return where.replace("[", "(").replace("]", ")");
    }
    
	public String translate(String target){
		RegCurrentParmMatch();
		return applyParams(target);
	}
	
}
