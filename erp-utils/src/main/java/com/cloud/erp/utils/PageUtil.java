/**
 * @Title:  PageUtil.java
 * @Package:  com.cloud.erp.utils
 * @Description:  
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
 * @ClassName PageUtil
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年2月4日 下午3:46:39
 *
 */
public class PageUtil {

	public Integer page;
	public Integer rows;
	
	public PageUtil() {

	}
	
	public PageUtil(Integer page, Integer rows){
		this.page = page;
		this.rows = rows;
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

}
