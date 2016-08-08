/**
 * @Title:  DepartmentService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:40:54
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.Department;
import com.cloud.erp.service.common.GeneralService;

/**
 * @ClassName  DepartmentService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月18日 上午10:40:54
 *
 */
public interface DepartmentService extends GeneralService<Department>{
	
	List<Department> findDepartments(Integer id);
	
	List<Department> findDepartments();
	
}
