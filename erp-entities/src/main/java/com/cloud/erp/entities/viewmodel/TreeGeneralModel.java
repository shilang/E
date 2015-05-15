/**
 * @Title:  TreeGeneralModel.java
 * @Package:  com.cloud.erp.entities.viewmodel
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月29日 下午6:06:10
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
 * @ClassName TreeGeneralModel
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年4月29日 下午6:06:10
 *
 */
public class TreeGeneralModel {

	private Integer id;
	private String text;
	private String iconCls;
	private String state;
	private boolean checked;
	private Object attributes;
	private List<TreeGeneralModel> children = new ArrayList<TreeGeneralModel>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<TreeGeneralModel> getChildren() {
		return children;
	}

	public void setChildren(List<TreeGeneralModel> children) {
		this.children = children;
	}

}
