/**
 * @Title:  EmployeeService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午10:01:24
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.table.Employee;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  EmployeeService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:01:24
 *
 */
public interface EmployeeService {

	List<Employee> findEmployees(Map<String, Object> params, PageUtil pageUtil);
	
	Long getCount(Map<String, Object> params, PageUtil pageUtil);
	
	boolean persistenceEmployee(Employee employee);
	
	boolean delEmployee(Integer id);
}
