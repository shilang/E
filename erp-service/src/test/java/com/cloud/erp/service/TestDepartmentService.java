package com.cloud.erp.service;


import java.util.List;

import org.apache.log4j.Logger;

import com.cloud.erp.dao.DepartmentDao;
import com.cloud.erp.dao.impl.DepartmentDaoImpl;
import com.cloud.erp.entities.Department;
import com.cloud.erp.logging.LogUtil;

public class TestDepartmentService {

	private Logger log = LogUtil.getLogger(this.getClass()); 
	private DepartmentDao departmentDao  = new DepartmentDaoImpl();
	
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public List<Department> getAllDepartments(){
		List<Department> depts = departmentDao.getAllDepartments();
		if (log.isDebugEnabled()){
			log.debug(depts);
		}
		return depts;
	}
}
