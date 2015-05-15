/**
 * @Title:  EmployeeDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.EmployeeDao;
import com.cloud.erp.entities.table.Employee;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.sun.tools.javac.code.Attribute.Constant;

/**
 * @ClassName  EmployeeDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:06:01
 *
 */
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	private BaseDao<Employee> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Employee> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.EmployeeDao#findEmployees(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Employee> findEmployees(Map<String, Object> params,
			PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "from Employee t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDao.find(hql, params, pageUtil.getPage(), pageUtil.getRows());
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.EmployeeDao#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Employee t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDao.count(hql, params);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.EmployeeDao#persistenceEmployee(com.cloud.erp.entities.table.Employee)
	 */
	@Override
	public boolean persistenceEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if(null == employee.getEmployeeId() || "".equals(employee.getEmployeeId())){
			employee.setCreated(new Date());
			employee.setCreater(userId);
			employee.setLastmod(new Date());
			employee.setModifier(userId);
			employee.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(employee);
		}else {
			employee.setLastmod(new Date());
			employee.setModifier(userId);
			baseDao.update(employee);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.EmployeeDao#delEmployee(java.lang.Integer)
	 */
	@Override
	public boolean delEmployee(Integer id) {
		// TODO Auto-generated method stub
		Employee employee = baseDao.get(Employee.class, id);
		employee.setLastmod(new Date());
		employee.setModifier(Constants.getCurrentUser().getUserId());
		employee.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		baseDao.deleteToUpdate(employee);
		
		return true;
	}

}
