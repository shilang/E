/**
 * @Title:  SalesOutStockDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午2:50:23
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
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICStockBill;

/**
 * @ClassName  SalesOutStockDao
 * @Description  销售出库单
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午2:50:23
 *
 */
public interface SalesOutStockDao extends 
			GeneralDao<ICStockBill>, 
			SingleEntryDao<ICStockBill>, 
			ReferenceDao<ICStockBill>{

	List<SalesShareEntry> findSalesOutStockNoticeEntriesById(Integer noticeId);
	
	List<SalesShareEntry> findSalesOrderEntriesById(Integer orderId);
	
	boolean updateSalesOutStockNoticeReference(String number, boolean mode) throws UpdateReferenceException;

	boolean updateSalesOrderReference(String number, boolean mode) throws UpdateReferenceException;

}
