/**
 * @Title:  SalesReturnGoodsNoticeDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年7月6日 下午3:59:45
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
 * @ClassName  SalesReturnGoodsNoticeDao
 * @Description  退货通知单
 * @author  bollen bollen@live.cn
 * @date  2015年7月6日 下午3:59:45
 *
 */
public interface SalesReturnGoodsNoticeDao extends 
					GeneralDao<SalesOutStock>,
					SingleEntryDao<SalesOutStock>,
					ReferenceDao<SalesOutStock>{
	
	List<SalesShareEntry> findSalesOutStockNoticeEntriesById(Integer parentId);

	boolean updateSalesOutStockNoticeReference(String number, boolean mode) throws UpdateReferenceException;
	
}
