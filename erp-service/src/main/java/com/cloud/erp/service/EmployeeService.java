/**
 * @Title:  EmployeeService.java
 * @Package:  com.cloud.erp.service
 * @Description: 
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

import com.cloud.erp.entities.table.Employee;
import com.cloud.erp.service.common.GeneralService;

/**
 * @ClassName  EmployeeService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:01:24
 *
 */
public interface EmployeeService extends GeneralService<Employee>{

	List<Employee> findEmployees();

}
