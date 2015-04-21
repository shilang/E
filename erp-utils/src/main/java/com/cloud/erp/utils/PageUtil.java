/**
 * @Title:  PageUtil.java
 * @Package:  com.cloud.erp.utils
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月4日  下午3:46:39
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.utils;

/**
 * @ClassName  PageUtil
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月4日  下午3:46:39
 *
 */
public class PageUtil {

	public Integer page;
	public Integer rows;
	public String searchAnds;
	public String searchColumnNames;
	public String searchConditions;
	public String searchVals;

	public PageUtil(Integer page, Integer rows, String searchAnds,
			String searchColumnNames, String searchConditions, String searchVals) {
		super();
		this.page = page;
		this.rows = rows;
		this.searchAnds = searchAnds;
		this.searchColumnNames = searchColumnNames;
		this.searchConditions = searchConditions;
		this.searchVals = searchVals;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSearchAnds() {
		return searchAnds;
	}

	public void setSearchAnds(String searchAnds) {
		this.searchAnds = searchAnds;
	}

	public String getSearchColumnNames() {
		return searchColumnNames;
	}

	public void setSearchColumnNames(String searchColumnNames) {
		this.searchColumnNames = searchColumnNames;
	}

	public String getSearchConditions() {
		return searchConditions;
	}

	public void setSearchConditions(String searchConditions) {
		this.searchConditions = searchConditions;
	}

	public String getSearchVals() {
		return searchVals;
	}

	public void setSearchVals(String searchVals) {
		this.searchVals = searchVals;
	}


}
