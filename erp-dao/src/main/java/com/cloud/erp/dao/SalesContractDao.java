/**
 * @Title:  SalesContractDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月15日 上午10:41:22
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.dao.common.MultiEntryDao;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.entities.table.SalesContract;

/**
 * @ClassName  SalesContractDao
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月15日 上午10:41:22
 *
 */
public interface SalesContractDao extends 
			GeneralDao<SalesContract>,
			MultiEntryDao<SalesContract>,
			ReferenceDao<SalesContract>
			{
}
