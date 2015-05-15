/**
 * @Title:  FunctionService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月13日  下午5:04:03
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.entities.viewmodel.TreeGridModel;
import com.cloud.erp.entities.viewmodel.TreeModel;

/**
 * @ClassName  FunctionService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月13日  下午5:04:03
 *
 */
public interface FunctionService {
	
	List<TreeGridModel> findAllFunctionList(Integer pid);
	
	boolean delFunction(Integer id);
	
	boolean persistenceFunction(List<Permission> list);
	
	List<TreeModel> findAllFunctionList();
	
	boolean persistenceFunction(Permission permission);
	
}
