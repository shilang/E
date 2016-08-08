/**
 * @Title:  SalesContractService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月15日 上午11:27:06
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

import com.cloud.erp.entities.table.SalesContract;
import com.cloud.erp.entities.table.SalesContractEntry;
import com.cloud.erp.entities.table.SalesContractScheme;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.service.common.GeneralService;
import com.cloud.erp.service.common.MultiEntryService;

/**
 * @ClassName SalesContractService
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年6月15日 上午11:27:06
 *
 */
public interface SalesContractService extends
	GeneralService<SalesContract>,
	AutoNumber<SalesContractEntry>,
	MultiEntryService<SalesContract>{
	
	boolean persistence(SalesContract salesContract,
			Map<String, List<SalesContractEntry>> entries,
			Map<String, List<SalesContractScheme>> schemes) throws Exception;
	
	boolean deleteToUpdateAll(Integer pid);
}
