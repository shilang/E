package com.cloud.erp.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.cloud.erp.entities.viewmodel.RequestParams;
import com.cloud.erp.entities.viewmodel.Result;
import com.cloud.erp.utils.PageUtil;

public class BaseHandler implements Request {

	private static final String TITLE = "提示";
	private static final String SUCCESS = "数据更新成功！";
	private static final String FAILURE = "数据更新失败！";
	private final RequestImpl request = new RequestImpl();

	@Override
	public Map<String, Object> getQueryParams(String searchName, String searchValue, String searchType, 
			String searchColumnNames, String searchConditions, String searchVals, String searchAnds,  
			String sort, String order) {
		
		return request.getQueryParams(searchName, searchValue, searchType, 
				searchColumnNames, searchConditions, searchVals, searchAnds,  
				sort, order);
	}

	@Override
	public <T> Map<String, List<T>> getEntriesParams(Class<T> clazz,
			String inserted, String updated, String deleted) {
		
		return request.getEntriesParams(clazz, inserted, updated, deleted);
	}
	
	public Map<String, Object> getQueryParams(RequestParams rp){
		return getQueryParams(rp.getSearchName(), rp.getSearchValue(), rp.getSearchType(),
				rp.getSearchColumnNames(), rp.getSearchConditions(), rp.getSearchVals(), rp.getSearchAnds(), 
				rp.getSort(), rp.getOrder());
	}
	
	@Override
	public PageUtil getPageUtil(RequestParams rp) {
		
		return request.getPageUtil(rp);
	}

	public Result getDefaultResult(boolean status) {
		Result result = null;
		if (status) {
			result = getResult(true, SUCCESS);
		} else {
			result = getResult(false, FAILURE);
		}
		return result;
	}

	public Result getResult(boolean status, String message) {
		return getResult(status, TITLE, message);
	}

	public Result getResult(boolean status, String title, String message) {
		Result result = new Result();
		result.setStatus(status);
		result.setTitle(title);
		result.setMessage(message);
		return result;

	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
	}
}
