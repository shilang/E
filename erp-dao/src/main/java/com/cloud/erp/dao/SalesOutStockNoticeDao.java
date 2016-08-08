/**
 * @Title:  SalesOutStockNoticeDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年7月3日 下午4:54:14
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
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDao;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.exceptions.UpdateReferenceException;

/**
 * @ClassName  SalesOutStockNoticeDao
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年7月3日 下午4:54:14
 *
 */
public interface SalesOutStockNoticeDao extends 
			GeneralDao<SalesOutStock>,
			SingleEntryDao<SalesOutStock>,
			ReferenceDao<SalesOutStock>{
	
	List<SalesShareEntry> findSalesOrderEntriesById(Integer orderId);
	
	boolean updateSalesOrderReference(String number, boolean mode) throws UpdateReferenceException;

}
