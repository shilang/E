/**
 * @Title:  AuxiliaryResMessageDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月5日 上午11:15:36
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

import com.cloud.erp.entities.table.AuxiliaryResMessage;

/**
 * @ClassName  AuxiliaryResMessageDao
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月5日 上午11:15:36
 *
 */
public interface AuxiliaryResMessageDao {
	
	List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId);
	
	Long getCount(Integer resId);
	
	boolean persistenceAuxiliaryResMessage(AuxiliaryResMessage auxiliaryResMessage);
	
	boolean delAuxiliaryResMessage(Integer id);
	
}
