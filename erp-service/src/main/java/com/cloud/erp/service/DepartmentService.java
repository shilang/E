/**
 * @Title:  DepartmentService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
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
import java.util.Map;

import com.cloud.erp.entities.table.Department;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  DepartmentService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月18日 上午10:40:54
 *
 */
public interface DepartmentService {
	
	List<Department> findDepartments(Map<String, Object> params, PageUtil pageUtil);
	
	List<Department> findDepartments(Integer id);
	
	long getCount(Map<String, Object> params, PageUtil pageUtil);
	
	boolean persistenceDepartment(Department department);
	
	boolean delDepartment(Integer id);
	
}
