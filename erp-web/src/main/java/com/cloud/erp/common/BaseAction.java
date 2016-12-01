/**
 * @Title:  BaseAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月4日  上午11:34:04
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.entities.viewmodel.RequestParams;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName BaseAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年2月4日 上午11:34:04
 *
 */

@ParentPackage("default-package")
@Results({@Result(name = "json", type = "json", params = {"root","jsonData"})})
public class BaseAction extends RequestParams implements Request, ActionAware {
	
	private static final long serialVersionUID = 1L;
	
	private final ActionSupport actionAware = new ActionSupport();
	
	private final RequestImpl request = new RequestImpl(); 
	
    private Object jsonData;
    
    public BaseAction() {
	}

    public Object getJsonData() {
		return jsonData;
	}

	public void setJsonData(Object jsonData) {
		this.jsonData = jsonData;
	}
	
	public Json getMessage(boolean flag) {
		String msg = "";
		if (flag) {
			msg = Constants.POST_DATA_SUCCESS;
		} else {
			msg = Constants.POST_DATA_FAIL;
		}	
		return getMessage(flag, msg);
	}
	
	public Json getMessage(boolean flag, String msg){
		Json json = new Json();
		json.setStatus(flag);
		json.setMessage(msg);
		return json;
	}
	
	public Json getCheck(boolean flag){
		Json json = new Json();
		if(flag){
			json.setStatus(true);
			json.setMessage(Constants.MESSAGE_CHECK_SUCCESS);
		}else {
			json.setStatus(false);
			json.setMessage(Constants.MESSAGE_CHECK_FAIL);
		}
		return json;
	}

	/**
	 * write object for grid
	 * @param rows
	 * @param total
	 */
	@SuppressWarnings("rawtypes")
	public void JSONWriter(List rows, Long total){
    	GridModel gridModel  = new GridModel();
    	gridModel.setRows(rows);
    	gridModel.setTotal(total);
    	JSONWriterGeneral(gridModel);
    }
	
	/**
	 * write object with boolean
	 * @param result
	 */
	public void JSONWriter(boolean result){
		JSONWriter(getMessage(result));
	}
    
	/**
	 * write object with status
	 * @param json
	 */
    public void JSONWriter(Json json){
    	JSONWriterGeneral(json);
    }
    
    public void JSONWriterError(String errMsg){
    	JSONWriter(getMessage(false, errMsg));
    }
    
    public void JSONWriterSuccess(String succMsg){
    	JSONWriter(getMessage(true, succMsg));
    }
    
    /**
     * write general object
     * @param object
     */
    public void JSONWriterGeneral(Object object){
    	setJsonData(object);
    }
    
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
	
	public <T> Map<String, List<T>> getEntriesParams(Class<T> clazz) {
		return getEntriesParams(clazz, getInserted(), getUpdated(), getDeleted());
	}

	@Override
	public PageUtil getPageUtil(RequestParams rp) {
		return request.getPageUtil(rp);
	}
	
	public PageUtil getPageUtil(){
		return new PageUtil(getPage(), getRows());
	}
	
	public Map<String, Object> getQueryParamsForMain(){
		return getQueryParams(getSearchName(), getSearchValue(), getSearchType(), getSearchColumnNames(),
				getSearchConditions(), getSearchVals(), getSearchAnds(), getSort(), getOrder());
		
	}

	public void OutputJson(Object object) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext.getResponse();

		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}

	public void OutputJson(Object object, String type) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext
				.getResponse();
		httpServletResponse.setContentType(type);
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;

		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}
	
	@Override
	public String execute() throws Exception {
		return actionAware.execute();
	}

}
