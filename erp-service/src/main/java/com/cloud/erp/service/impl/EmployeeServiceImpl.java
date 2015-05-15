/**
 * @Title:  EmployeeServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
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
 * @ClassName  EmployeeServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:03:33
 *
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	
	/**
	 * @param employeeDao the employeeDao to set
	 */
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.EmployeeService#findEmployees(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Employee> findEmployees(Map<String, Object> params,
			PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployees(params, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.EmployeeService#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return employeeDao.getCount(params, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.EmployeeService#persistenceEmployee(com.cloud.erp.entities.table.Employee)
	 */
	@Override
	public boolean persistenceEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.persistenceEmployee(employee);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.EmployeeService#delEmployee(java.lang.Integer)
	 */
	@Override
	public boolean delEmployee(Integer id) {
		// TODO Auto-generated method stub
		return employeeDao.delEmployee(id);
	}

}
