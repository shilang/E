/**
 * @Title:  SalesOrderService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午2:08:26
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

import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.entities.table.SalesOrderEntry;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.service.common.GeneralService;
import com.cloud.erp.service.common.ShareEntryService;
import com.cloud.erp.service.common.SingleEntryService;

/**
 * @ClassName  SalesOrderService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午2:08:26
 *
 */
public interface SalesOrderService extends
	GeneralService<SalesOrder>,
	AutoNumber<SalesOrderEntry>,
	SingleEntryService<SalesOrder>,
	ShareEntryService<SalesShareEntry, SalesOrderEntry>{
	
	boolean persistence(SalesOrder salesOrder, Map<String, List<SalesOrderEntry>> entries) throws Exception;
	
	boolean deleteToUpdateAll(Integer pid) throws Exception;
	
}
