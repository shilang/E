/**
 * @Title:  DepartmentServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
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
import com.cloud.erp.service.DepartmentService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName DepartmentServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年3月18日 上午10:45:33
 *
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> findDepartments(Integer id) {
		return departmentDao.findDepartments(id);
	}

	@Override
	public List<Department> findDepartments() {
		return departmentDao.findAll(null, null);
	}

	@Override
	public List<Department> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return departmentDao.findAll(params, pageUtil);
	}

	@Override
	public Department get(Integer id) {
		return departmentDao.get(id);
	}

	@Override
	public void update(Department master) {
		departmentDao.update(master);
	}

	@Override
	public boolean persistence(Department master) throws Exception {
		return departmentDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return departmentDao.deleteToUpdate(pid);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return departmentDao.getCount(params);
	}

}
