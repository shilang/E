/**
 * @Title:  ParameterModel.java
 * @Package:  com.cloud.erp.entities.viewmodel
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午9:26:45
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities.viewmodel;

import java.util.Date;

/**
 * @ClassName  ParameterModel
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月31日 上午9:26:45
 *
 */
public class ParameterModel {

	private Integer parameterId;
	private String myid;
	private String name;
	private String value;
	private String description;
	private String group;
	private String state;
	private String status;
	private Object editor;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifier;
	private String editorType;
	
	public Integer getParameterId() {
		return parameterId;
	}
	
	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}
	
	public String getMyid() {
		return myid;
	}
	
	public void setMyid(String myid) {
		this.myid = myid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Object getEditor() {
		return editor;
	}
	
	public void setEditor(Object editor) {
		this.editor = editor;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getLastmod() {
		return lastmod;
	}
	
	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}
	
	public Integer getCreater() {
		return creater;
	}
	
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	
	public Integer getModifier() {
		return modifier;
	}
	
	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}
	
	public String getEditorType() {
		return editorType;
	}
	
	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}
	
}
