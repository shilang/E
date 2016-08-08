/**
 * @Title:  SalesOutStockService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午3:00:55
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
import com.cloud.erp.entities.table.ICStockBill;
import com.cloud.erp.entities.table.ICStockBillEntry;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.service.common.GeneralService;
import com.cloud.erp.service.common.ShareEntryService;
import com.cloud.erp.service.common.SingleEntryService;

/**
 * @ClassName  SalesOutStockService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午3:00:55
 *
 */
public interface SalesOutStockService extends 
	GeneralService<ICStockBill>,
    AutoNumber<ICStockBillEntry>,
    SingleEntryService<ICStockBill>,
    ShareEntryService<SalesShareEntry, ICStockBillEntry>{
	
	boolean persistence(ICStockBill icStockBill, Map<String, List<ICStockBillEntry>> entries) throws Exception;
	
	boolean deleteToUpdateAll(Integer pid) throws Exception; 
}
