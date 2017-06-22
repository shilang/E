package com.cloud.erp.common;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.viewmodel.RequestParams;
import com.cloud.erp.utils.PageUtil;

/**
 * 
 * @author Bollen
 *
 */
public interface Request {

	Map<String, Object> getQueryParams(String searchName, String searchValue, String searchType, 
			String searchColumnNames, String searchConditions, String searchVals, String searchAnds,  
			String sort, String order);
	
	public <T> Map<String, List<T>> getEntriesParams(Class<T> clazz, String inserted,String updated, String deleted);
	
    PageUtil getPageUtil(RequestParams rp);
	
}
