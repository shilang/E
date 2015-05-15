/**
 * @Title:  AuxiliaryResMessageService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
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
import java.util.Map;

import com.cloud.erp.entities.table.AuxiliaryResMessage;

/**
 * @ClassName  AuxiliaryResMessageService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月5日 上午11:11:23
 *
 */
public interface AuxiliaryResMessageService {
	
	List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId);
	
	Long getCount(Integer resId);
	
	boolean persistenceAuxiliaryResMessage(AuxiliaryResMessage auxiliaryResMessage);
	
	boolean delAuxiliaryResMessage(Integer id);
	
}
