/**
 * @Title:  AuxiliaryResAssignService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月15日 上午11:06:09
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.AuxiliaryResMessage;

/**
 * @ClassName  AuxiliaryResAssignService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月15日 上午11:06:09
 *
 */
public interface AuxiliaryResAssignService {

	List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId);
	
	long getCount(Integer resId);
	
}
