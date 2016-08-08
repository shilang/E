/**
 * @Title:  EmployeeDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description: 
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午10:06:01
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.EmployeeDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Employee;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  EmployeeDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:06:01
 *
 */
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("employeeId");
	}
	
	@Resource
	private GeneralDaoSupport<Employee> generalDao;

	@Autowired
	private BaseDao<Employee> baseDao;

	@Override
	public List<Employee> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(Employee.class, params, pageUtil);
	}
	
	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Employee.class, params);
	}
	
	@Override
	public Employee get(Integer id) {
		return generalDao.get(Employee.class, id);
	}
	
	@Override
	public void update(Employee master) {
		generalDao.update(master);
	}
	
	@Override
	public boolean persistence(Employee master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}
	
	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Employee.class, pid, statusFields);
	}

}
