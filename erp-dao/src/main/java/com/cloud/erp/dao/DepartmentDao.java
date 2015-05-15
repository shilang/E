/**
 * @Title:  DepartmentDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:47:40
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

import com.cloud.erp.entities.table.Department;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName DepartmentDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月18日 上午10:47:40
 *
 */
public interface DepartmentDao {
	
	List<Department> findDepartments(Map<String, Object> params, PageUtil pageUtil);
	
	List<Department> findDepartments(Integer id);
	
	long getCount(Map<String, Object> params, PageUtil pageUtil);
	
	boolean persistenceDepartment(Department department);
	
	boolean delDepartment(Integer id);
}
