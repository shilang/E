/**
 * @Title:  EmployeeServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午10:03:33
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

import com.cloud.erp.dao.EmployeeDao;
import com.cloud.erp.entities.table.Employee;
import com.cloud.erp.service.EmployeeService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName EmployeeServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午10:03:33
 *
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> findEmployees() {
		return employeeDao.findAll(null, null);
	}

	@Override
	public List<Employee> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return employeeDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return employeeDao.getCount(params);
	}

	@Override
	public Employee get(Integer id) {
		return employeeDao.get(id);
	}

	@Override
	public void update(Employee master) {
		employeeDao.update(master);
	}

	@Override
	public boolean persistence(Employee master) throws Exception {
		return employeeDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return employeeDao.deleteToUpdate(pid);
	}
}
