/**
 * @Title:  DepartmentDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
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

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.entities.table.Department;

/**
 * @ClassName DepartmentDao
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年3月18日 上午10:47:40
 *
 */
public interface DepartmentDao extends GeneralDao<Department>{
	
	List<Department> findDepartments(Integer id);
	
}
