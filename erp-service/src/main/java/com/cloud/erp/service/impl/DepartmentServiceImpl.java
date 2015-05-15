/**
 * @Title:  DepartmentServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:45:33
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.DepartmentDao;
import com.cloud.erp.entities.table.Department;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.service.DepartmentService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName DepartmentServiceImpl
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月18日 上午10:45:33
 *
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao departmentDao;

	/**
	 * @param departmentDao
	 *            the departmentDao to set
	 */
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cloud.erp.service.DepartmentService#findDepartments(java.util.Map,
	 * com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Department> findDepartments(Map<String, Object> params,
			PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return departmentDao.findDepartments(params, pageUtil);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cloud.erp.service.DepartmentService#findDepartments(java.lang.Integer
	 * )
	 */
	@Override
	public List<Department> findDepartments(Integer id) {
		// TODO Auto-generated method stub
		return departmentDao.findDepartments(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.service.DepartmentService#getCount(java.util.Map,
	 * com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return departmentDao.getCount(params, pageUtil);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cloud.erp.service.DepartmentService#persistenceDepartment(com.cloud
	 * .erp.entities.table.Department)
	 */
	@Override
	public boolean persistenceDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.persistenceDepartment(department);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cloud.erp.service.DepartmentService#delDepartment(java.lang.Integer)
	 */
	@Override
	public boolean delDepartment(Integer id) {
		// TODO Auto-generated method stub
		return departmentDao.delDepartment(id);
	}

}
