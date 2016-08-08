/**
 * @Title:  SalesOrderDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月19日 下午2:45:25
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
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.exceptions.UpdateReferenceException;


/**
 * @ClassName  SalesOrderDao
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月19日 下午2:45:25
 *
 */
public interface SalesOrderDao extends 
			GeneralDao<SalesOrder>,
			SingleEntryDao<SalesOrder>,
			ReferenceDao<SalesOrder>{
	
	List<SalesShareEntry> findContractEntriesById(Integer contractId);
	
	List<SalesShareEntry> findPriceListEntriesById(Integer priceListId);
	
	boolean updatePriceListReference(String number, boolean mode) throws UpdateReferenceException;
	
	boolean updateContractReference(String number, boolean mode) throws UpdateReferenceException;
	
}
