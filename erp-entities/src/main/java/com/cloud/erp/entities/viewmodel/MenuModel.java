/**
 * @Title:  MenuModel.java
 * @Package:  com.cloud.erp.viewmodel
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月4日  下午5:28:26
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities.viewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName  MenuModel
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月4日  下午5:28:26
 *
 */
public class MenuModel {

	private String id;
	private String pid;
	private String name;
	private String iconCls;
	private String url;
	private List<MenuModel> child = new ArrayList<MenuModel>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuModel> getChild() {
		return child;
	}
	public void setChild(List<MenuModel> child) {
		this.child = child;
	}
			
	
	
}
