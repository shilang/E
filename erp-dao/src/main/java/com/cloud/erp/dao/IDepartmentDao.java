package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.Department;

public interface IDepartmentDao {
	
	public Department getDepartment();

	public List<Department> getDepartments();
	
}
