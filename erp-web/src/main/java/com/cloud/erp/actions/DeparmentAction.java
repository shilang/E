package com.cloud.erp.actions;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.cloud.erp.service.DepartmentService;

public class DeparmentAction implements RequestAware {
	
	private DepartmentService departmentService = new DepartmentService();
	
	public void list(){
		request.put("departments", departmentService.getAllDepartments());
	}

	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
