/**
 * @Title:  FunctionDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
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

import com.cloud.erp.entities.Permission;
import com.cloud.erp.entities.viewmodel.TreeGridModel;
import com.cloud.erp.entities.viewmodel.TreeModel;

/**
 * @ClassName FunctionDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年2月13日 下午5:24:45
 *
 */
public interface FunctionDao {

	List<TreeGridModel> findAllFunctionList(Integer pid);

	boolean delFunction(Integer id);

	boolean persistenceFunction(List<Permission> list);

	List<TreeModel> findAllFunctionList();

	boolean persistenceFunction(Permission permission);

}
