/**
 * @Title:  AuxiliaryResAssignDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月15日 上午11:03:07
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.table.AuxiliaryResMessage;

/**
 * @ClassName  AuxiliaryResAssignDao
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月15日 上午11:03:07
 *
 */
public interface AuxiliaryResAssignDao {

	List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId);
	
	long getCount(Integer resId);
}
