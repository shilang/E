package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.Department;

public interface DepartmentDao {
	
	public Department getDepartment(Integer deptId);
	
	public List<Department> getAllDepartments();
	
	public void saveDepartment(Department department);
}
