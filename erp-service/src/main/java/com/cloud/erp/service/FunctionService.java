/**
 * @Title:  FunctionService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
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
import com.cloud.erp.service.common.GeneralService;

/**
 * @ClassName  FunctionService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月13日  下午5:04:03
 *
 */
public interface FunctionService extends GeneralService<Permission>{
	
	List<Permission> findFunctionsById(Integer pid);

	List<Permission> findFunctions();
	
}
