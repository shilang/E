package com.cloud.erp.actions;

import java.util.Map;





import org.apache.struts2.interceptor.RequestAware;

import com.cloud.erp.entities.Department;
import com.cloud.erp.service.DepartmentService;

public class DeparmentAction implements RequestAware {
	
	
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public String list(){
		request.put("departments", departmentService.getAllDepartments());
		
		return "success";
	}
	
	public String save(){
		Department department = new Department();
		department.setDid(1);
		department.setName("bollen");
		department.setNumber(123456);
		department.setRemarks("This is a testing");
		
		departmentService.saveDepartment(department);
		
		return "success";
	}

	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
