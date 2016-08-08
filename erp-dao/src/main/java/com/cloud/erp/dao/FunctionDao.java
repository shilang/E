/**
 * @Title:  FunctionDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月13日  下午5:24:45
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
import com.cloud.erp.entities.table.Permission;

/**
 * @ClassName FunctionDao
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年2月13日 下午5:24:45
 *
 */
public interface FunctionDao extends GeneralDao<Permission>{
	
	List<Permission> findAllWithExtHql();

	List<Permission> findFunctionsById(Integer pid);
}
