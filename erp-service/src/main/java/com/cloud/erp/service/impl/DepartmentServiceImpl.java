package com.cloud.erp.service.impl;

import java.util.List;

import com.cloud.erp.dao.IDepartmentDao;
import com.cloud.erp.dao.impl.DepartmentDaoImpl;
import com.cloud.erp.entities.Department;
import com.cloud.erp.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {

	private IDepartmentDao departmentDao = new DepartmentDaoImpl();
	
	@Override
	public List<Department> getDepartments() {
		departmentDao
	}

}
