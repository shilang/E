/**
 * @Title:  SalesPriceListService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月22日 上午10:29:25
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
import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.entities.table.SalesPriceListEntry;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.service.common.GeneralService;
import com.cloud.erp.service.common.ShareEntryService;
import com.cloud.erp.service.common.SingleEntryService;


/**
 * @ClassName  SalesPriceListService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年4月22日 上午10:29:25
 *
 */
public interface SalesPriceListService extends
	GeneralService<SalesPriceList>,
	AutoNumber<SalesPriceListEntry>,
	SingleEntryService<SalesPriceList>,
	ShareEntryService<SalesShareEntry, SalesPriceListEntry>{
	
	boolean persistence(SalesPriceList salesPriceList, Map<String, List<SalesPriceListEntry>> entries) throws Exception;
	
	boolean deleteToUpdateAll(Integer pid) throws Exception;

}
