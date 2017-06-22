package com.cloud.erp.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cloud.erp.entities.viewmodel.RequestParams;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * 
 * @author Bollen
 *
 */
public class RequestImpl implements Request {

	@Override
	public Map<String, Object> getQueryParams(String searchName, String searchValue, String searchType, 
			String searchColumnNames, String searchConditions, String searchVals, String searchAnds,  
			String sort, String order) {
		Map<String, Object> params = new HashMap<String, Object>();
		//add simple search
		if(null != searchValue && !"".equals(searchValue)){
			String simpleSearch = searchName + Constants.SEARCH_PARAM_SEP + searchValue + Constants.SEARCH_PARAM_SEP
					+ (searchType == null?"string":searchType);
			params.put(Constants.SEARCH_PARAM_TYPE_SIMPLE, simpleSearch);
		}
		
		//add high grade search
		if(null != searchVals && !"".equals(searchVals)){
			String highSearch = searchColumnNames + Constants.SEARCH_PARAM_SEP + searchConditions 
					+ Constants.SEARCH_PARAM_SEP + searchVals + Constants.SEARCH_PARAM_SEP + searchAnds;
			params.put(Constants.SEARCH_PARAM_TYPE_HIGHGRADE, highSearch);
		}
		
		//add sort
		if(null != sort && !"".equals(sort)){
			String sortString = sort + Constants.SEARCH_PARAM_SEP + order;
			params.put(Constants.SEARCH_PARAM_TYPE_SORT, sortString);
		}
		
		return params;
	}

	@Override
	public <T> Map<String, List<T>> getEntriesParams(Class<T> clazz, String inserted,String updated, String deleted) {
		Map<String, List<T>> entries = new HashMap<String, List<T>>();
		entries.put(Constants.ENTRY_LIST_TYPE_ADD, JSON.parseArray(inserted, clazz));
		entries.put(Constants.ENTRY_LIST_TYPE_UPD, JSON.parseArray(updated, clazz));
		entries.put(Constants.ENTRY_LIST_TYPE_DEL, JSON.parseArray(deleted, clazz));
		return entries;
	}

	@Override
	public PageUtil getPageUtil(RequestParams rp) {
		return new PageUtil(rp.getPage(), rp.getRows());
	}

}
