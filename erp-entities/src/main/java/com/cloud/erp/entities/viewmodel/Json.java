/**
 * @Title:  Json.java
 * @Package:  com.cloud.erp.viewmodel
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月4日  下午1:47:51
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities.viewmodel;

/**
 * @ClassName  Json
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月4日  下午1:47:51
 *
 */
public class Json {

	private String title = "提示";
	private String message;
	private boolean status = false;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
