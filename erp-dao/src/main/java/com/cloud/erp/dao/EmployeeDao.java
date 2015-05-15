/**
 * @Title:  EmployeeDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午10:04:24
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.table.Employee;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  EmployeeDao
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:04:24
 *
 */
public interface EmployeeDao {

	List<Employee> findEmployees(Map<String, Object> params, PageUtil pageUtil);
	
	Long getCount(Map<String, Object> params, PageUtil pageUtil);
	
	boolean persistenceEmployee(Employee employee);
	
	boolean delEmployee(Integer id);
	
}
