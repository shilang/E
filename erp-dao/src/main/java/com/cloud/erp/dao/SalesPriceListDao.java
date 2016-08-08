/**
 * @Title:  SalesPriceDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月21日 下午2:24:42
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDao;
import com.cloud.erp.entities.table.SalesPriceList;

/**
 * @ClassName  SalesPriceDao
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年4月21日 下午2:24:42
 *
 */
public interface SalesPriceListDao 
				extends 
				GeneralDao<SalesPriceList>, 
				SingleEntryDao<SalesPriceList>, 
				ReferenceDao<SalesPriceList>{
	
}
