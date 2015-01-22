package com.cloud.erp.service;


import java.util.List;

import com.cloud.erp.dao.DepartmentDao;
import com.cloud.erp.dao.impl.DepartmentDaoImpl;
import com.cloud.erp.entities.Department;

public class DepartmentService {

	private DepartmentDao departmentDao  = new DepartmentDaoImpl();
	
	public List<Department> getAllDepartments(){
		return departmentDao.getAllDepartments();
	}
}
