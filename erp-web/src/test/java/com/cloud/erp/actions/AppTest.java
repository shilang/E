package com.cloud.erp.actions;

import com.cloud.erp.service.DepartmentService;

import junit.framework.TestCase;

public class AppTest extends TestCase {

	public void test(){

		DepartmentService departmentService = new DepartmentService();
		
		assertNotNull(departmentService.getAllDepartments());
	}
}
