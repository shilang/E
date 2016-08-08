/**
 * @Title:  CompanyInfoService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月28日 上午10:23:21
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.CompanyInfo;
import com.cloud.erp.service.common.GeneralService;

/**
 * @ClassName CompanyInfoService
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月28日 上午10:23:21
 *
 */
public interface CompanyInfoService extends GeneralService<CompanyInfo>{

	List<CompanyInfo> findCompanyInfos();
	
	long getCount();
	
}
