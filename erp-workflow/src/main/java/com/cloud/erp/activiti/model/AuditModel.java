package com.cloud.erp.activiti.model;

import java.io.Serializable;

public class AuditModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String processInstanceId;

	/**
	 * commit, amend, check, review, change
	 */
	private String taskBusinessType;

	/**
	 * view path
	 */
	private String path;
	private Integer businessKey;
	private String businessClass;
	
	/**
	 * business number
	 */
	private String number;
	
	/**
	 * task creater
	 */
	private String creater;
	
	/**
	 * check result
	 * none, approve, reject
	 */
	private String result;
	
	/**
	 * check remark
	 */
	private String remark;
	
	private String extra;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskBusinessType() {
		return taskBusinessType;
	}

	public void setTaskBusinessType(String taskBusinessType) {
		this.taskBusinessType = taskBusinessType;
	}
	
	public Integer getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(Integer businessKey) {
		this.businessKey = businessKey;
	}
	
	public String getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getExtra() {
		return extra;
	}
	
	public void setExtra(String extra) {
		this.extra = extra;
	}

}
