/**
 * @Title:  AuxiliaryResMessageService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月5日 上午11:11:23
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
import com.cloud.erp.service.common.GeneralService;

/**
 * @ClassName  AuxiliaryResMessageService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月5日 上午11:11:23
 *
 */
public interface AuxiliaryResMessageService extends GeneralService<AuxiliaryResMessage>{
	
	List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId);
	
	Long getCount(Integer resId);
	
}
