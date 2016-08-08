/**
 * @Title:  SystemCodeService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月30日 上午10:18:43
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.SystemCode;
import com.cloud.erp.entities.viewmodel.TreeModel;

/**
 * @ClassName  SystemCodeService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月30日 上午10:18:43
 *
 */
public interface SystemCodeService {
	
	List<SystemCode> findSystemCodeList(Integer id);
	
	List<TreeModel> findSystemCodeList();
	
	boolean persistenceSystemCodeDig(SystemCode systemCode, String permissionName, Integer codePid);
	
	boolean delSystemCode(Integer codeId);
	
	List<SystemCode> findSystemCodeByType(String codeMyid);
	
}
